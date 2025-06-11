package net.highspeedtrain.createlawandorder.registry;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CreateLawAndOrder.MOD_ID);

    public static final RegistryObject<SoundEvent> GAVEL_USE = registerSoundEvents("gavel_use");

    @SuppressWarnings("removal")
    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CreateLawAndOrder.MOD_ID, name)));
    }
    
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
