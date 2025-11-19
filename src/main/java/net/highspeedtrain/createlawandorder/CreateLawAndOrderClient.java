package net.highspeedtrain.createlawandorder;

import net.highspeedtrain.createlawandorder.content.ui.CourtTerminalScreen;
import net.highspeedtrain.createlawandorder.core.registry.MenuRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CreateLawAndOrder.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreateLawAndOrderClient {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(MenuRegistry.COURT_TERMINAL_MENU.get(), CourtTerminalScreen::new);
    }
}
