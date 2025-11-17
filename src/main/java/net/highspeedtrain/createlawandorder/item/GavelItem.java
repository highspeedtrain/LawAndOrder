package net.highspeedtrain.createlawandorder.item;

import net.highspeedtrain.createlawandorder.registry.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GavelItem extends Item {
    public GavelItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            BlockPos positionClicked = player.getOnPos();

            level.playSeededSound(
                null, 
                positionClicked.getX(),  
                positionClicked.getY(), 
                positionClicked.getZ(),
                SoundRegistry.GAVEL_USE.get(), 
                SoundSource.PLAYERS, 
                1, 
                1, 
                0
            );
            player.getCooldowns().addCooldown(this, 120);
        }

        return super.use(level, player, interactionHand);
    }
}