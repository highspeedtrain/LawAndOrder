package net.highspeedtrain.createlawandorder.content.villager;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Pair;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CreateLawAndOrder.MOD_ID)
public class VillageHouses {
    private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_RESOURCE_KEY = 
        ResourceKey.create(Registries.PROCESSOR_LIST, ResourceLocation.fromNamespaceAndPath("minecraft", "empty"));

    @SuppressWarnings("all")
    private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistries, Registry<StructureProcessorList> processorListRegistry,
        ResourceLocation poolResourceLocation,
        String nbtPieceResourceLocation,
        int weight
    ) {
        Holder<StructureProcessorList> emptyProcessorList = processorListRegistry.getHolderOrThrow(EMPTY_PROCESSOR_RESOURCE_KEY);
        StructureTemplatePool pool = templatePoolRegistries.get(poolResourceLocation);
        if (pool == null) {
            return;
        }

        SinglePoolElement piece = SinglePoolElement.legacy(nbtPieceResourceLocation, emptyProcessorList).apply(StructureTemplatePool.Projection.RIGID);

        for (int i = 0; i < weight; i++) {
            pool.templates.add(piece);
        }

        List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
        listOfPieceEntries.add(new Pair<>(piece, weight));
        pool.rawTemplates = listOfPieceEntries;
    }

    @SubscribeEvent
    public static void addBuildings(ServerAboutToStartEvent event) {
        Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registryOrThrow(Registries.TEMPLATE_POOL);
        Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess().registryOrThrow(Registries.PROCESSOR_LIST);

        addBuildingToPool(
            templatePoolRegistry, 
            processorListRegistry, 
            ResourceLocation.parse("minecraft:village/plains/houses"), 
            "createlawandorder:attorney_general_house_plains", 
            100
        );
    }
}
