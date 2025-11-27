package net.highspeedtrain.createlawandorder.core.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.core.datagen.advancement.AdvancementsGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateLawAndOrder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new RecipesProvider(output));
        generator.addProvider(event.includeServer(), LootTablesProvider.create(output));

        generator.addProvider(event.includeClient(), new BlockStatesProvider(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemModelsProvider(output, existingFileHelper));
        generator.addProvider(event.includeServer(), new PoiTagsProvider(output, lookupProvider, existingFileHelper));
        
        BlockTagProvider blockTagProv = generator.addProvider(event.includeServer(), 
            new BlockTagProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemTagProvider(output, lookupProvider, blockTagProv.contentsGetter(), existingFileHelper));

        generator.addProvider(
            event.includeServer(), 
            new ForgeAdvancementProvider(
                output, 
                lookupProvider, 
                existingFileHelper, 
                List.of(new AdvancementsGenerator())
            )
        );

        CreateLawAndOrder.LOGGER.info("curseforge will NEVER know this was a duplicate!!!");
    }
}
