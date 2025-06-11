package net.highspeedtrain.createlawandorder;

import net.highspeedtrain.createlawandorder.registry.CreativeTab;
import net.highspeedtrain.createlawandorder.registry.ItemRegistry;
import net.highspeedtrain.createlawandorder.registry.SoundRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateLawAndOrder.MOD_ID)
public class CreateLawAndOrder {
    public static final String MOD_ID = "createlawandorder";

    @SuppressWarnings("removal")
    public CreateLawAndOrder() {
        System.out.println("[CREATE LAW AND ORDER] Running version DEV 1.0.0");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.register(modEventBus);
        CreativeTab.register(modEventBus);
        SoundRegistry.register(modEventBus);
    }
}