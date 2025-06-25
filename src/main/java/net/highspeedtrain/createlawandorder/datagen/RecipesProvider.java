package net.highspeedtrain.createlawandorder.datagen;

import java.util.function.Consumer;

import com.simibubi.create.AllItems;

import net.highspeedtrain.createlawandorder.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class RecipesProvider extends RecipeProvider implements IConditionBuilder  {

    public RecipesProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.FEATHER_PEN.get())
            .pattern("I")
            .pattern("F")
            .define('I', Items.INK_SAC)
            .define('F', Items.FEATHER)
            .unlockedBy("has_feather", has(Items.FEATHER))
            .save(pWriter);
        
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegistry.GAVEL.get())
            .pattern("WWW")
            .pattern(" B ")
            .pattern(" W ")
            .define('B', AllItems.BRASS_INGOT)
            .define('W', ItemTags.PLANKS)
            .unlockedBy("has_feather", has(Items.FEATHER))
            .save(pWriter);
    }
}
