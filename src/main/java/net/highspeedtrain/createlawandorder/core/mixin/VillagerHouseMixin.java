package net.highspeedtrain.createlawandorder.core.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.datafixers.util.Pair;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

@Mixin(StructureTemplatePool.class)
public class VillagerHouseMixin {
    @Shadow @Final private ResourceLocation name;
    @Shadow @Final private List<Pair<StructurePoolElement, Integer>> rawTemplates;
    @Shadow @Final private List<StructurePoolElement> templates;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void addStructures(CallbackInfo callbackInfo) {
        if (this.name.equals(ResourceLocation.parse("minecraft:village/plains/houses"))) {
            StructurePoolElement element = SinglePoolElement.single(CreateLawAndOrder.MOD_ID+":village/plains/houses/attorney_general_house")
                .apply(StructureTemplatePool.Projection.RIGID);

            this.templates.add(element);
            this.rawTemplates.add(new Pair<>(element, 10));
        }
    }
}
