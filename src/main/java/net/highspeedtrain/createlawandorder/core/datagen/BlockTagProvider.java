package net.highspeedtrain.createlawandorder.core.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.core.registry.BlockRegistry;
import net.highspeedtrain.createlawandorder.core.registry.Tags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagProvider extends BlockTagsProvider {
    public BlockTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CreateLawAndOrder.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider p_256380_) {
        this.tag(Tags.Blocks.NEEDS_PICKAXE)
            .add(BlockRegistry.BLUE_BLOCK.get());

        this.tag(Tags.Blocks.CAN_WIRE_BLOCK)
            .add(BlockRegistry.BLUE_BLOCK.get());

        this.tag(Tags.Blocks.NEEDS_IRON_TOOL)
            .add(BlockRegistry.BLUE_BLOCK.get());
    }
}
