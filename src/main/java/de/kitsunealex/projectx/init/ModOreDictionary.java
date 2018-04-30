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

import de.kitsunealex.projectx.util.EnumXycroniumColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

public class ModOreDictionary {

    public static void registerEntries() {
        for(int i = 0; i < 5; i++) {
            String color = StringUtils.capitalize(EnumXycroniumColor.values()[i].getName());
            OreDictionary.registerOre(String.format("oreXycronium%s", color), new ItemStack(ModBlocks.XYCRONIUM_ORE, 1, i));
            OreDictionary.registerOre(String.format("blockXycronium%s", color), new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, i));
            OreDictionary.registerOre(String.format("crystalXycronium%s", color), new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 1, i));
            OreDictionary.registerOre(String.format("ingotXycronium%s", color), new ItemStack(ModItems.XYCRONIUM_INGOT, 1, i));
            OreDictionary.registerOre(String.format("dustXycronium%s", color), new ItemStack(ModItems.XYCRONIUM_DUST, 1, i));
            OreDictionary.registerOre(String.format("nuggetXycronium%s", color), new ItemStack(ModItems.XYCRONIUM_NUGGET, 1, i));
        }
    }

}
