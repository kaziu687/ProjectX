package de.kitsunealex.projectx.recipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;
import java.util.Map;

public class ShapedRecipe {

    private String group;
    private ItemStack output;
    private List<String> ingredients = Lists.newArrayList();
    private Map<Character, ItemStack> ingredientMap = Maps.newHashMap();
    private NonNullList<Ingredient> ingredientList = NonNullList.create();
    private int width = 0;
    private boolean hasMissingIngredients = false;
    private boolean isPatternDefined = false;

    public ShapedRecipe(String group, ItemStack output, Object... params) {
        this.group = group;
        this.output = output;

        for(int i = 0; i < params.length; i++) {
            Object param = params[i];

            if(param instanceof String) {
                if(!isPatternDefined) {
                    ingredients.add((String)param);
                }
            }
            else if(param instanceof Character) {
                if(!isPatternDefined) {
                    isPatternDefined = true;
                }

                Object nextParam = params[i + 1];

                if(nextParam instanceof Item) {
                    ingredientMap.put((Character)param, new ItemStack((Item)nextParam, 1, 0));
                }
                else if(nextParam instanceof Block) {
                    ingredientMap.put((Character)param, new ItemStack((Block) nextParam, 1, 0));
                }
                else if(nextParam instanceof ItemStack) {
                    ingredientMap.put((Character)param, (ItemStack)nextParam);
                }
            }
        }

        createIngredients();
    }

    private void createIngredients() {
        for(String row : ingredients) {
            if(width < row.length()) {
                width = row.length();
            }

            for(int i = 0; i < row.length(); i++) {
                if(row.charAt(i) != ' ') {
                    ItemStack ingredient = ingredientMap.get(row.charAt(i));

                    if(ingredient != null) {
                        ingredientList.add(Ingredient.fromStacks(ingredient));
                    }
                    else {
                        hasMissingIngredients = true;
                    }
                }
            }
        }
    }

    public void registerRecipe(ResourceLocation registryName) {
        if(!hasMissingIngredients) {
            ShapedRecipes recipe = new ShapedRecipes(group, width, ingredients.size(), ingredientList, output);
            recipe.setRegistryName(registryName);
            ForgeRegistries.RECIPES.register(recipe);
        }
        else {
            throw new RecipeException("Recipe '%s' is missing an ingredient!", registryName.toString());
        }
    }

}
