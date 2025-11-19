package net.highspeedtrain.createlawandorder.core.datagen.advancement;

import java.util.function.Consumer;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.core.registry.ItemRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

public class AdvancementsGenerator implements ForgeAdvancementProvider.AdvancementGenerator {

    @SuppressWarnings({ "unused" })
    @Override
    public void generate(Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        Advancement test = Advancement.Builder.advancement()
            .display(
                new ItemStack(ItemRegistry.GAVEL.get()),
                Component.translatable("createlawandorder.advancement.equal_justice_under_law.title"),
                Component.translatable("createlawandorder.advancement.equal_justice_under_law.desc"),
                CreateLawAndOrder.path("minecraft:textures/gui/advancements/backgrounds/stone.png"),
                FrameType.TASK,
                true,
                true,
                false
            )
            .addCriterion("obtain_gavel", InventoryChangeTrigger.TriggerInstance.hasItems(ItemRegistry.GAVEL.get()))
            .save(saver, CreateLawAndOrder.modPath("equal_justice_under_law"), existingFileHelper);
    }
}
