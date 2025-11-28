package net.highspeedtrain.createlawandorder.content.item;

import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
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
    public BlockPos firstSelection = null;
    @Nullable
    public BlockPos secondSelection = null;

    public static final Supplier<Set<Block>> ALLOWED_BLOCKS = () -> Set.of(
        BlockRegistry.COURT_TERMINAL.get()
    );
    
    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Block block = ctx.getLevel().getBlockState(ctx.getClickedPos()).getBlock();
        Player player = ctx.getPlayer();

        if (!ALLOWED_BLOCKS.get().contains(block)) {
            if (!ctx.getLevel().isClientSide()) {
                player.sendSystemMessage(Component.translatable("createlawandorder.wire.use.notallowed").withStyle(ChatFormatting.RED));
            }
            return InteractionResult.FAIL;
        }

        CreateLawAndOrder.LOGGER.info("hello i am server doing stuff");
        return InteractionResult.SUCCESS;
    }
}
