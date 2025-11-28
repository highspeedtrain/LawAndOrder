package net.highspeedtrain.createlawandorder;

import net.highspeedtrain.createlawandorder.content.network.CLONetworking;
import net.highspeedtrain.createlawandorder.core.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
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
        MenuRegistry.register(modEventBus);
        CLONetworking.register();

        LOGGER.info("Hello, World!");
    }

    public static ResourceLocation modPath(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static ResourceLocation path(String path) {
        return ResourceLocation.parse(path);
    }
}