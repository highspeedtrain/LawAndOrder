package net.highspeedtrain.createlawandorder;

import net.highspeedtrain.createlawandorder.registry.*;
import net.highspeedtrain.createlawandorder.villager.VillagerProfessions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod(CreateLawAndOrder.MOD_ID)
public class CreateLawAndOrder {
    public static final String MOD_ID = "createlawandorder";
    public static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public CreateLawAndOrder() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.register(modEventBus);
        ItemRegistry.register(modEventBus);
        CreativeTab.register(modEventBus);
        SoundRegistry.register(modEventBus);
        VillagerProfessions.register(modEventBus);
        LOGGER.info("["+MOD_ID+"] registry complete.");
    }

    @SubscribeEvent
    public void OnServerStarting() {
        LOGGER.info("["+MOD_ID+"] initialised.");
    }
}