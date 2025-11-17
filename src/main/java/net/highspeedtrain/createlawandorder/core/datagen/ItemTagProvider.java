package net.highspeedtrain.createlawandorder.core.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagProvider extends ItemTagsProvider{

    public ItemTagProvider(PackOutput p_275343_, CompletableFuture<Provider> p_275729_, 
            CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, CreateLawAndOrder.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(Provider p_256380_) {
        
    }
}
