package de.kitsunealex.projectx.init;

import de.kitsunealex.projectx.recipe.RecipeHandler;
import net.minecraft.item.ItemStack;

public class ModCrafting {

    public static void registerRecipes() {
        for(int i = 0; i < 5; i++) {
            RecipeHandler.addRecipe("projectx", new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, i), "XXX", "XXX", "XXX", 'X', new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 1, i));
        }
    }

}
