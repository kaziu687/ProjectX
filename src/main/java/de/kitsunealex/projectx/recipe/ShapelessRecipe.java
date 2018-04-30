/*
 * This file is part of ProjectX.
 * Copyright (c) 2015 - 2018, KitsuneAlex, All rights reserved.
 *
 * ProjectX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ProjectX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ProjectX.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package de.kitsunealex.projectx.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ShapelessRecipe implements ICustomRecipe {

    private String group;
    private ItemStack output;
    private Object[] params;
    private NonNullList<Ingredient> ingredientList = NonNullList.create();

    public ShapelessRecipe(String group, ItemStack output, Object... params) {
        this.group = group;
        this.output = output;
        this.params = params;
    }

    @Override
    public void processIngredients() {
        for(int i = 0; i < params.length; i++) {
            Object param = params[i];

            if(param instanceof Item) {
                ingredientList.add(Ingredient.fromStacks(new ItemStack((Item)param, 1, OreDictionary.WILDCARD_VALUE)));
            }
            else if(param instanceof Block) {
                ingredientList.add(Ingredient.fromStacks(new ItemStack((Block)param, 1, OreDictionary.WILDCARD_VALUE)));
            }
            else if(param instanceof ItemStack) {
                ingredientList.add(Ingredient.fromStacks((ItemStack)param));
            }
            else if(param instanceof String) {
                NonNullList<ItemStack> stacks = OreDictionary.getOres((String)param);
                ingredientList.add(Ingredient.fromStacks(stacks.toArray(new ItemStack[stacks.size()])));
            }
            else {
                throw new RecipeException("Unknown ingredient type '%s'!", param.getClass().getName());
            }
        }
    }

    @Override
    public void register(ResourceLocation registryName) {
        ShapelessRecipes recipe = new ShapelessRecipes(group, output, ingredientList);
        recipe.setRegistryName(registryName);
        ForgeRegistries.RECIPES.register(recipe);
    }

    @Override
    public boolean hasMissingIngredients() {
        return false;
    }

    @Override
    public boolean isPatternDefined() {
        return true;
    }

}
