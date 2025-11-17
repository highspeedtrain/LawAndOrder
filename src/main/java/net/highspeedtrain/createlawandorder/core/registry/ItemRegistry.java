package net.highspeedtrain.createlawandorder.core.registry;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.content.item.GavelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateLawAndOrder.MOD_ID);

    public static final RegistryObject<Item> FEATHER_PEN = ITEMS.register("feather_pen",
        () -> new Item(new Item.Properties()
        .rarity(Rarity.COMMON))
        );
            
    public static final RegistryObject<GavelItem> GAVEL = ITEMS.register("gavel",
        () -> new GavelItem(new Item.Properties()
        .rarity(Rarity.UNCOMMON)
        .stacksTo(1)
        ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}