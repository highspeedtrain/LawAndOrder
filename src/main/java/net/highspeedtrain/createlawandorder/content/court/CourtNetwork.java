package net.highspeedtrain.createlawandorder.content.court;

import net.highspeedtrain.createlawandorder.content.block.CourtCoreBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourtNetwork extends SavedData {

    private static final String ID = "court_network";
    private final Map<BlockPos, List<BlockPos>> courts = new HashMap<>();

    public CourtNetwork(CompoundTag tag) {
        courts.clear();
        ListTag courtList = tag.getList("Courts", Tag.TAG_COMPOUND);

        for (Tag cTag : courtList) {
            CompoundTag courtTag = (CompoundTag) cTag;
            BlockPos key = readPos(courtTag.getCompound("Key"));
            List<BlockPos> connections = new ArrayList<>();
            ListTag list = courtTag.getList("Connections", Tag.TAG_COMPOUND);

            for (Tag connection : list) {
                connections.add(readPos((CompoundTag) connection));
            }

            courts.put(key, connections);
        }
    }

    public CourtNetwork() {}

    public static CourtNetwork get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                CourtNetwork::new,
                CourtNetwork::new,
                ID
        );
    }

    public Map<BlockPos, List<BlockPos>> getCourts() {
        return courts;
    }

    public List<BlockPos> getConnections(BlockPos pos) {
        return courts.getOrDefault(pos, List.of());
    }

    public void addConnection(BlockPos core, BlockPos otherBlock) {
        List<BlockPos> list = courts.computeIfAbsent(core, connections -> new ArrayList<>());

        if (!list.contains(otherBlock)) {
            list.add(otherBlock);
            setDirty();
        }
    }

    public void clearAllConnections(BlockPos core) {
        courts.remove(core);
        setDirty();
    }

    public void removeConnection(BlockPos core, BlockPos otherBlock) {
        List<BlockPos> list = courts.get(core);
        if (list == null) {
            return;
        }

        if (list.remove(otherBlock)) {
            if (list.isEmpty()) {
                courts.remove(core);
            }

            setDirty();
        }
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag compoundTag) {
        ListTag courtList = new ListTag();

        for (Map.Entry<BlockPos, List<BlockPos>> entry : courts.entrySet()) {
            CompoundTag courtTag = new CompoundTag();
            courtTag.put("Key", writePos(entry.getKey()));
            ListTag connectionsList = new ListTag();

            for (BlockPos pos : entry.getValue()) {
                connectionsList.add(writePos(pos));
            }

            courtTag.put("Connections", connectionsList);
            courtList.add(courtTag);
        }

        compoundTag.put("Courts", courtList);
        return compoundTag;
    }

    private static CompoundTag writePos(BlockPos pos) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("x", pos.getX());
        tag.putInt("y", pos.getY());
        tag.putInt("z", pos.getZ());
        return tag;
    }

    private static BlockPos readPos(CompoundTag tag) {
        return new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
    }
}