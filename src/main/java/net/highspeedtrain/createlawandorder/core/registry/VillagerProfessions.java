package net.highspeedtrain.createlawandorder.core.registry;

import com.google.common.collect.ImmutableSet;
import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillagerProfessions {
    public static final DeferredRegister<PoiType> POI_TYPES =
        DeferredRegister.create(ForgeRegistries.POI_TYPES, CreateLawAndOrder.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFS =
        DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, CreateLawAndOrder.MOD_ID);

    public static final RegistryObject<PoiType> ATTORNEYGENERAL_POI = POI_TYPES.register("attorneygeneral_poi",
        () -> new PoiType(ImmutableSet.copyOf(BlockRegistry.BLUE_BLOCK.get().getStateDefinition().getPossibleStates()),
        1, 1));
    
    
    public static final RegistryObject<VillagerProfession> ATTORNEY_GENERAL =
            VILLAGER_PROFS.register("attorney_general", () -> new VillagerProfession("attorney_general",
                    holder -> holder.get() == ATTORNEYGENERAL_POI.get(), holder -> holder.get() == ATTORNEYGENERAL_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_CLERIC));

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFS.register(eventBus);
    }
}
