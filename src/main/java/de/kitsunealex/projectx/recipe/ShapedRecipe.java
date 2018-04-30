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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collection;
import java.util.List;

public class ShapedRecipe implements ICustomRecipe {

    private String group;
    private ItemStack output;
    private Object[] params;
    private List<String> ingredients = Lists.newArrayList();
    private NonNullList<Ingredient> ingredientList = NonNullList.create();
    private int width = 0;
    private boolean hasMissingIngredients = false;
    private boolean isPatternDefined = false;

    public ShapedRecipe(String group, ItemStack output, Object... params) {
        this.group = group;
        this.output = output;
        this.params = params;
    }

    @Override
    public void processIngredients() {
        Multimap<Character, ItemStack> ingredientMap = Multimaps.newListMultimap(Maps.newHashMap(), Lists::newArrayList);

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
                    ingredientMap.put((Character)param, new ItemStack((Item)nextParam, 1, OreDictionary.WILDCARD_VALUE));
                }
                else if(nextParam instanceof Block) {
                    ingredientMap.put((Character)param, new ItemStack((Block) nextParam, 1, OreDictionary.WILDCARD_VALUE));
                }
                else if(nextParam instanceof ItemStack) {
                    ingredientMap.put((Character)param, (ItemStack)nextParam);
                }
                else if(nextParam instanceof String) {
                    NonNullList<ItemStack> items = OreDictionary.getOres((String)nextParam);
                    items.forEach(item -> ingredientMap.put((Character)param, item));
                }
                else {
                    throw new RecipeException("Unknown ingredient type '%s'!", nextParam.getClass().getName());
                }
            }
        }

        for(String row : ingredients) {
            if(width < row.length()) {
                width = row.length();
            }

            for(int i = 0; i < row.length(); i++) {
                if(row.charAt(i) != ' ') {
                    Collection<ItemStack> stacks = ingredientMap.get(row.charAt(i));

                    if(stacks != null && !stacks.isEmpty()) {
                        ingredientList.add(Ingredient.fromStacks(stacks.toArray(new ItemStack[stacks.size()])));
                    }
                    else {
                        hasMissingIngredients = true;
                    }
                }
            }
        }
    }

    @Override
    public void register(ResourceLocation registryName) {
        if(!hasMissingIngredients) {
            ShapedRecipes recipe = new ShapedRecipes(group, width, ingredients.size(), ingredientList, output);
            recipe.setRegistryName(registryName);
            ForgeRegistries.RECIPES.register(recipe);
        }
        else {
            throw new RecipeException("Recipe '%s' is missing an ingredient!", registryName.toString());
        }
    }

    @Override
    public boolean hasMissingIngredients() {
        return hasMissingIngredients;
    }

    @Override
    public boolean isPatternDefined() {
        return isPatternDefined;
    }

}
