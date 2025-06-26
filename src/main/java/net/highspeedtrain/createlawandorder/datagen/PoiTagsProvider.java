package net.highspeedtrain.createlawandorder.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PoiTagsProvider extends PoiTypeTagsProvider {

    public PoiTagsProvider(PackOutput p_256012_, CompletableFuture<Provider> p_256617_,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256012_, p_256617_, CreateLawAndOrder.MOD_ID, existingFileHelper);
    }

    @SuppressWarnings("removal")
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
            .addOptional(new ResourceLocation("createlawandorder:attorneygeneral_poi"));
    }
}