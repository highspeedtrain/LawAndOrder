package net.highspeedtrain.createlawandorder.core.registry;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateLawAndOrder.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CREATE_LAW_AND_ORDER_TAB =
            CREATIVE_MODE_TABS.register("createlawandordermain", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.createlawandorder"))
                .icon(() -> new ItemStack(ItemRegistry.GAVEL.get()))
                .displayItems((features, output) -> {
                    output.accept(ItemRegistry.FEATHER_PEN.get());
                    output.accept(ItemRegistry.GAVEL.get());
                    output.accept(ItemRegistry.WIRE.get());

                    output.accept(BlockRegistry.BLUE_BLOCK.get());
                    output.accept(BlockRegistry.COURT_TERMINAL.get());
                    output.accept(BlockRegistry.COURT_CORE.get());
                })
                .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}