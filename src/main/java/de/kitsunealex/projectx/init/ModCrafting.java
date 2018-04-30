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

package de.kitsunealex.projectx.init;

import de.kitsunealex.projectx.recipe.RecipeHandler;
import de.kitsunealex.projectx.util.EnumXycroniumColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.StringUtils;

public class ModCrafting {

    public static void registerRecipes() {
        for(int i = 0; i < 5; i++) {
            String color = StringUtils.capitalize(EnumXycroniumColor.values()[i].getName());

            RecipeHandler.addRecipe(new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, i),
                    "XXX", "XXX", "XXX",
                    'X', String.format("crystalXycronium%s", color)
            );
            RecipeHandler.addRecipe(new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, i),
                    "XXX", "XXX", "XXX",
                    'X', String.format("ingotXycronium%s", color)
            );
            RecipeHandler.addRecipe(new ItemStack(ModBlocks.XYCRONIUM_BRICKS, 1, i),
                    "IXI", "XXX", "IXI",
                    'I', String.format("ingotXycronium%s", color),
                    'X', Blocks.STONEBRICK
            );
            RecipeHandler.addRecipe(new ItemStack(ModBlocks.INFUSED_BRICKS, 1, i),
                    "IXI", "XXX", "IXI",
                    'I', String.format("dustXycronium%s", color),
                    'X', Blocks.STONEBRICK
            );
            RecipeHandler.addRecipe(new ItemStack(ModItems.XYCRONIUM_INGOT, 1, i),
                    "XXX", "XXX", "XXX",
                    'X', new ItemStack(ModItems.XYCRONIUM_NUGGET, 1, i)
            );

            RecipeHandler.addShapelessRecipe(new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 9, i), new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, i));
            RecipeHandler.addShapelessRecipe(new ItemStack(ModItems.XYCRONIUM_NUGGET, 9, i), new ItemStack(ModItems.XYCRONIUM_INGOT, 1, i));

            GameRegistry.addSmelting(new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 1, i), new ItemStack(ModItems.XYCRONIUM_INGOT, 1, i), 0.25F);
            GameRegistry.addSmelting(new ItemStack(ModItems.XYCRONIUM_DUST, 1, i), new ItemStack(ModItems.XYCRONIUM_INGOT, 1, i), 0.25F);
        }

        RecipeHandler.addRecipe(new ItemStack(ModBlocks.GLASS_VIEWER, 1, 0),
                "IXI", "XXX", "IXI",
                'I', "ingotIron",
                'X', "blockGlassColorless"
        );
    }

}
