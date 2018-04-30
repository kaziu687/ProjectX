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

package de.kitsunealex.projectx.util;

import codechicken.lib.util.ItemNBTUtils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class StackUtils {

    public static ItemStack makeStack(Item item, IVoidFunction<NBTTagCompound> function) {
        return makeStack(item, 1, function);
    }

    public static ItemStack makeStack(Item item, int amount, IVoidFunction<NBTTagCompound> function) {
        return makeStack(item, amount, 0, function);
    }

    public static ItemStack makeStack(Item item, int amount, int meta, IVoidFunction<NBTTagCompound> function) {
        ItemStack stack = new ItemStack(item, amount, meta);
        ItemNBTUtils.validateTagExists(stack);
        function.apply(stack.getTagCompound());
        return stack;
    }

    public static ItemStack makeStack(Block block, IVoidFunction<NBTTagCompound> function) {
        return makeStack(block, 1, function);
    }

    public static ItemStack makeStack(Block block, int amount, IVoidFunction<NBTTagCompound> function) {
        return makeStack(block, amount, 0, function);
    }

    public static ItemStack makeStack(Block block, int amount, int meta, IVoidFunction<NBTTagCompound> function) {
        ItemStack stack = new ItemStack(block, amount, meta);
        ItemNBTUtils.validateTagExists(stack);
        function.apply(stack.getTagCompound());
        return stack;
    }

}
