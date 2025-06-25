package net.highspeedtrain.createlawandorder.datagen;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemModelsProvider extends ItemModelProvider {

    public ItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreateLawAndOrder.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemRegistry.FEATHER_PEN);
        simpleItem(ItemRegistry.GAVEL);
    }

    @SuppressWarnings("removal")
    private <T extends Item> ItemModelBuilder simpleItem(RegistryObject<T> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CreateLawAndOrder.MOD_ID,"item/" + item.getId().getPath()));
    }
}
