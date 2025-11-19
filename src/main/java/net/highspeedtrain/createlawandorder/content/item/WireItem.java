package net.highspeedtrain.createlawandorder.content.item;

import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.highspeedtrain.createlawandorder.core.registry.BlockRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class WireItem extends Item {
    public WireItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Nullable
    public static BlockPos firstSelection = null;
    @Nullable
    public static BlockPos secondSelection = null;

    public static final Supplier<Set<Block>> ALLOWED_BLOCKS = () -> Set.of(
        BlockRegistry.COURT_TERMINAL.get()
    );
    
    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if (ctx.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        Block block = ctx.getLevel().getBlockState(ctx.getClickedPos()).getBlock();
        Player player = ctx.getPlayer();

        if (!ALLOWED_BLOCKS.get().contains(block)) {
            player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.notallowed").withStyle(ChatFormatting.RED));
            return InteractionResult.SUCCESS;
        }

        if (firstSelection == null) {
            firstSelection = ctx.getClickedPos();
            player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.firstpos").withStyle(ChatFormatting.RED));
            return InteractionResult.SUCCESS;
        }

        if (secondSelection == null) {
            secondSelection = ctx.getClickedPos();
            player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.secondpos").withStyle(ChatFormatting.RED));
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
