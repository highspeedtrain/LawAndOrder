package net.highspeedtrain.createlawandorder.content.item;

import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.content.block.CourtCoreBlock;
import net.highspeedtrain.createlawandorder.content.court.CourtNetwork;
import net.highspeedtrain.createlawandorder.core.registry.BlockRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WireItem extends Item {
    public WireItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Nullable
    public BlockPos firstSelection = null;
    @Nullable
    public BlockPos secondSelection = null;

    public static final Supplier<Set<Block>> ALLOWED_BLOCKS = () -> Set.of(
        BlockRegistry.COURT_TERMINAL.get(),
        BlockRegistry.COURT_CORE.get()
    );
    
    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if (ctx.getLevel().isClientSide()) {
            return InteractionResult.PASS;
        }

        Block block = ctx.getLevel().getBlockState(ctx.getClickedPos()).getBlock();
        Player player = ctx.getPlayer();
        assert player != null;

        if (!ALLOWED_BLOCKS.get().contains(block)) {
            player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.notallowed").withStyle(ChatFormatting.RED));
            return InteractionResult.PASS;
        }

        if (firstSelection == null) {
            firstSelection = ctx.getClickedPos();
            player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.firstpos").withStyle(ChatFormatting.GREEN));
            return InteractionResult.PASS;
        } else if (secondSelection == null) {
            Block firstBlock = blockByPos(firstSelection, ctx);
            Block secondBlock = blockByPos(ctx.getClickedPos(), ctx);

            if (ctx.getClickedPos().equals(firstSelection)) {
                player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.same").withStyle(ChatFormatting.RED));
                firstSelection = null;
                return InteractionResult.FAIL;
            }

            secondSelection = ctx.getClickedPos();

            if (!(firstBlock instanceof CourtCoreBlock) && !(secondBlock instanceof CourtCoreBlock)) {
                player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.nocores").withStyle(ChatFormatting.RED));
                firstSelection = null;
                secondSelection = null;
                return InteractionResult.FAIL;
            }

            if (firstBlock.equals(secondBlock) && (firstBlock instanceof CourtCoreBlock)) {
                player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.toomanycores").withStyle(ChatFormatting.RED));
                firstSelection = null;
                secondSelection = null;
                return InteractionResult.FAIL;
            }

            BlockPos corePos = null;
            BlockPos otherPos = null;

            if (firstBlock instanceof CourtCoreBlock) {
                corePos = firstSelection;
                otherPos = secondSelection;
            } else {
                corePos = secondSelection;
                otherPos = firstSelection;
            }

            CourtNetwork.get((ServerLevel) ctx.getLevel()).addConnection(corePos, otherPos);
            player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.created").withStyle(ChatFormatting.GREEN));
            firstSelection = null;
            secondSelection = null;
        }

        return InteractionResult.PASS;
    }

    public static Block blockByPos(BlockPos pos, UseOnContext ctx) {
        if (pos == null) {
            return null;
        }
        return ctx.getLevel().getBlockState(pos).getBlock();
    }
}
