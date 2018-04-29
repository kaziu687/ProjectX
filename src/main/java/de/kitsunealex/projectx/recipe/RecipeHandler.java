package de.kitsunealex.projectx.recipe;

import de.kitsunealex.projectx.util.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RecipeHandler {

    private static int shapedIndex = 0;
    private static int shapedOreIndex = 0;
    private static int shapelessIndex = 0;
    private static int shapelessOreIndex = 0;

    public static void addRecipe(String group, ItemStack output, Object... params) {
        ResourceLocation name = new ResourceLocation(Constants.MODID, String.format("shaped_%d", shapedIndex++));
        ShapedRecipe recipe = new ShapedRecipe(group, output, params);
        recipe.registerRecipe(name);
    }

}
