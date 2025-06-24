package net.highspeedtrain.createlawandorder.datagen;

import java.util.concurrent.CompletableFuture;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
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
        
        BlockTagProvider blockTagProv = generator.addProvider(event.includeServer(), 
            new BlockTagProvider(output, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new RecipesProvider(output));
        generator.addProvider(event.includeServer(), new ItemTagProvider(output, lookupProvider, blockTagProv.contentsGetter(), existingFileHelper));
    }
}
