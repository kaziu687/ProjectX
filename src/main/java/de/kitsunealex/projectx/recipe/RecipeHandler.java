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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import de.kitsunealex.projectx.util.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class RecipeHandler {

    private static List<ICustomRecipe> recipes = Lists.newArrayList();

    public static void addRecipe(ItemStack output, Object... params) {
        recipes.add(new ShapedRecipe(Constants.MODID, output, params));
    }

    public static void addShapelessRecipe(ItemStack output, Object... params) {
        recipes.add(new ShapelessRecipe(Constants.MODID, output, params));
    }

    public static void registerRecipes() {
        for(int i = 0; i < recipes.size(); i++) {
            ICustomRecipe recipe = recipes.get(i);
            recipe.processIngredients();
            String name = String.format("custom_recipe_%d", i);
            recipe.register(new ResourceLocation(Constants.MODID, name));
        }
    }

    public static ImmutableList<ICustomRecipe> getRecipes() {
        return ImmutableList.copyOf(recipes);
    }

}
