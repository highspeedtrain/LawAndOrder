package net.highspeedtrain.createlawandorder.core.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.mojang.authlib.GameProfile;

import net.highspeedtrain.createlawandorder.core.util.DevStuff;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

@Mixin(AbstractClientPlayer.class)
public abstract class DevCapeMixin extends Player {

    @Shadow
    protected abstract PlayerInfo getPlayerInfo();

    public DevCapeMixin(Level p_250508_, BlockPos p_250289_, float p_251702_, GameProfile p_252153_) {
        super(p_250508_, p_250289_, p_251702_, p_252153_);
    }

    /**
     * @author highspeedtrain
     * @reason dev cape
     */
    @Nullable
    @Overwrite
    public ResourceLocation getElytraTextureLocation() {
        if (DevStuff.DEVS.contains(this.getUUID().toString()) || this.getGameProfile().getName().equals("Dev")) {
            return DevStuff.DEV_CAPE_TEXTURE;
        }

        PlayerInfo playerinfo = this.getPlayerInfo();
        return playerinfo == null ? null : playerinfo.getElytraLocation();
    }

    /**
     * @author highspeedtrain
     * @reason dev cape
     */
    @Nullable
    @Overwrite
    public ResourceLocation getCloakTextureLocation() {
        if (DevStuff.DEVS.contains(this.getUUID().toString()) || this.getGameProfile().getName().equals("Dev")) {
            return DevStuff.DEV_CAPE_TEXTURE;
        }

        PlayerInfo playerinfo = this.getPlayerInfo();
        return playerinfo == null ? null : playerinfo.getCapeLocation();
    }
}
