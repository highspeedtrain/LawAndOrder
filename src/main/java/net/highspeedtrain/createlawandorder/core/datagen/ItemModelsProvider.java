package net.highspeedtrain.createlawandorder.core.datagen;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.core.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
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

    private <T extends Item> ItemModelBuilder simpleItem(RegistryObject<T> item) {
        return withExistingParent(item.getId().getPath(),
                CreateLawAndOrder.path("item/generated")).texture("layer0",
                CreateLawAndOrder.modPath("item/" + item.getId().getPath()));
    }
}
