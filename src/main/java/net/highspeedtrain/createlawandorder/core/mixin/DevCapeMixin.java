package net.highspeedtrain.createlawandorder.core.mixin;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.highspeedtrain.createlawandorder.content.dev.DevList;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Mixin(CapeLayer.class)
public class DevCapeMixin extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public DevCapeMixin(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    /**
     * @author highspeedtrain
     * @reason render cape
     */
    @Overwrite
    public void render(@NotNull PoseStack stack, @NotNull MultiBufferSource buf, int packedLight,
        AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, 
        float ageInTicks, float netHeadYaw, float headPitch
    ) {
        if (!DevList.DEVS.contains(player.getUUID().toString()) && !player.getGameProfile().getName().equals("Dev")) {
            this.renderVanilla(stack, buf, packedLight, player, limbSwing, limbSwingAmount,
                partialTicks, ageInTicks, netHeadYaw, headPitch);
            return;
        }

        if (player.isCapeLoaded() && !player.isInvisible() && player.isModelPartShown(PlayerModelPart.CAPE)) {
            ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);

            if (!itemStack.is(Items.ELYTRA)) {
                stack.pushPose();
                stack.translate(0.0F, 0.0F, 0.125F);
                double d0 = Mth.lerp((double) partialTicks, player.xCloakO, player.xCloak)
                        - Mth.lerp((double) partialTicks, player.xo, player.getX());
                double d1 = Mth.lerp((double) partialTicks, player.yCloakO, player.yCloak)
                        - Mth.lerp((double) partialTicks, player.yo, player.getY());
                double d2 = Mth.lerp((double) partialTicks, player.zCloakO, player.zCloak)
                        - Mth.lerp((double) partialTicks, player.zo, player.getZ());
                float f = Mth.rotLerp(partialTicks, player.yBodyRotO, player.yBodyRot);
                double d3 = (double) Mth.sin(f * ((float) Math.PI / 180F));
                double d4 = (double) (-Mth.cos(f * ((float) Math.PI / 180F)));
                float f1 = (float) d1 * 10.0F;
                f1 = Mth.clamp(f1, -6.0F, 32.0F);
                float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
                f2 = Mth.clamp(f2, 0.0F, 150.0F);
                float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;
                f3 = Mth.clamp(f3, -20.0F, 20.0F);
                if (f2 < 0.0F) {
                    f2 = 0.0F;
                }

                float f4 = Mth.lerp(partialTicks, player.oBob, player.bob);
                f1 += Mth.sin(Mth.lerp(partialTicks, player.walkDistO, player.walkDist) * 6.0F) * 32.0F * f4;
                if (player.isCrouching()) {
                    f1 += 25.0F;
                }

                stack.mulPose(Axis.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
                stack.mulPose(Axis.ZP.rotationDegrees(f3 / 2.0F));
                stack.mulPose(Axis.YP.rotationDegrees(180.0F - f3 / 2.0F));
                VertexConsumer vertexconsumer = buf.getBuffer(RenderType.entitySolid(DevList.DEV_CAPE_TEXTURE));
                this.getParentModel().renderCloak(stack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
                stack.popPose();
            }
        }
    }

    public void renderVanilla(PoseStack p_116615_, MultiBufferSource p_116616_, int p_116617_, AbstractClientPlayer p_116618_, float p_116619_, float p_116620_, float p_116621_, float p_116622_, float p_116623_, float p_116624_) {
      if (p_116618_.isCapeLoaded() && !p_116618_.isInvisible() && p_116618_.isModelPartShown(PlayerModelPart.CAPE) && p_116618_.getCloakTextureLocation() != null) {
         ItemStack itemstack = p_116618_.getItemBySlot(EquipmentSlot.CHEST);
         if (!itemstack.is(Items.ELYTRA)) {
            p_116615_.pushPose();
            p_116615_.translate(0.0F, 0.0F, 0.125F);
            double d0 = Mth.lerp((double)p_116621_, p_116618_.xCloakO, p_116618_.xCloak) - Mth.lerp((double)p_116621_, p_116618_.xo, p_116618_.getX());
            double d1 = Mth.lerp((double)p_116621_, p_116618_.yCloakO, p_116618_.yCloak) - Mth.lerp((double)p_116621_, p_116618_.yo, p_116618_.getY());
            double d2 = Mth.lerp((double)p_116621_, p_116618_.zCloakO, p_116618_.zCloak) - Mth.lerp((double)p_116621_, p_116618_.zo, p_116618_.getZ());
            float f = Mth.rotLerp(p_116621_, p_116618_.yBodyRotO, p_116618_.yBodyRot);
            double d3 = (double)Mth.sin(f * ((float)Math.PI / 180F));
            double d4 = (double)(-Mth.cos(f * ((float)Math.PI / 180F)));
            float f1 = (float)d1 * 10.0F;
            f1 = Mth.clamp(f1, -6.0F, 32.0F);
            float f2 = (float)(d0 * d3 + d2 * d4) * 100.0F;
            f2 = Mth.clamp(f2, 0.0F, 150.0F);
            float f3 = (float)(d0 * d4 - d2 * d3) * 100.0F;
            f3 = Mth.clamp(f3, -20.0F, 20.0F);
            if (f2 < 0.0F) {
               f2 = 0.0F;
            }

            float f4 = Mth.lerp(p_116621_, p_116618_.oBob, p_116618_.bob);
            f1 += Mth.sin(Mth.lerp(p_116621_, p_116618_.walkDistO, p_116618_.walkDist) * 6.0F) * 32.0F * f4;
            if (p_116618_.isCrouching()) {
               f1 += 25.0F;
            }

            p_116615_.mulPose(Axis.XP.rotationDegrees(6.0F + f2 / 2.0F + f1));
            p_116615_.mulPose(Axis.ZP.rotationDegrees(f3 / 2.0F));
            p_116615_.mulPose(Axis.YP.rotationDegrees(180.0F - f3 / 2.0F));
            VertexConsumer vertexconsumer = p_116616_.getBuffer(RenderType.entitySolid(p_116618_.getCloakTextureLocation()));
            this.getParentModel().renderCloak(p_116615_, vertexconsumer, p_116617_, OverlayTexture.NO_OVERLAY);
            p_116615_.popPose();
         }
      }
   }
}
