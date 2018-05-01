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

package de.kitsunealex.projectx.compat.thaumcraft;

import de.kitsunealex.projectx.compat.IModule;
import de.kitsunealex.projectx.init.ModBlocks;
import de.kitsunealex.projectx.init.ModConfig;
import de.kitsunealex.projectx.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ModuleThaumcraft implements IModule {

    @Override
    public boolean isEnabled() {
        return ModConfig.COMPAT_THAUMCRAFT;
    }

    @Override
    public String getDependencyModID() {
        return "thaumcraft";
    }

    @Override
    public String getName() {
        return "Thaumcraft";
    }

    @Override
    public void handlePreInit(FMLPreInitializationEvent event) {
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 1, 0), new AspectList().add(Aspect.WATER, 1).add(Aspect.CRYSTAL, 4).add(Aspect.EARTH, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.CRYSTAL, 4).add(Aspect.EARTH, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 1, 2), new AspectList().add(Aspect.FIRE, 1).add(Aspect.CRYSTAL, 4).add(Aspect.EARTH, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 1, 3), new AspectList().add(Aspect.VOID, 1).add(Aspect.CRYSTAL, 4).add(Aspect.EARTH, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_CRYSTAL, 1, 4), new AspectList().add(Aspect.LIGHT, 1).add(Aspect.CRYSTAL, 4).add(Aspect.EARTH, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_INGOT, 1, 0), new AspectList().add(Aspect.WATER, 1).add(Aspect.METAL, 4).add(Aspect.CRYSTAL, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_INGOT, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.METAL, 4).add(Aspect.CRYSTAL, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_INGOT, 1, 2), new AspectList().add(Aspect.FIRE, 1).add(Aspect.METAL, 4).add(Aspect.CRYSTAL, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_INGOT, 1, 3), new AspectList().add(Aspect.VOID, 1).add(Aspect.METAL, 4).add(Aspect.CRYSTAL, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_INGOT, 1, 4), new AspectList().add(Aspect.LIGHT, 1).add(Aspect.METAL, 4).add(Aspect.CRYSTAL, 2));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_DUST, 1, 0), new AspectList().add(Aspect.WATER, 1).add(Aspect.TOOL, 1).add(Aspect.CRYSTAL, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_DUST, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.TOOL, 1).add(Aspect.CRYSTAL, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_DUST, 1, 2), new AspectList().add(Aspect.FIRE, 1).add(Aspect.TOOL, 1).add(Aspect.CRYSTAL, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_DUST, 1, 3), new AspectList().add(Aspect.VOID, 1).add(Aspect.TOOL, 1).add(Aspect.CRYSTAL, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_DUST, 1, 4), new AspectList().add(Aspect.LIGHT, 1).add(Aspect.TOOL, 1).add(Aspect.CRYSTAL, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_NUGGET, 1, 0), new AspectList().add(Aspect.WATER, 1).add(Aspect.METAL, 2).add(Aspect.CRYSTAL, 1).add(Aspect.CRAFT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_NUGGET, 1, 1), new AspectList().add(Aspect.PLANT, 1).add(Aspect.METAL, 2).add(Aspect.CRYSTAL, 1).add(Aspect.CRAFT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_NUGGET, 1, 2), new AspectList().add(Aspect.FIRE, 1).add(Aspect.METAL, 2).add(Aspect.CRYSTAL, 1).add(Aspect.CRAFT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_NUGGET, 1, 3), new AspectList().add(Aspect.VOID, 1).add(Aspect.METAL, 2).add(Aspect.CRYSTAL, 1).add(Aspect.CRAFT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModItems.XYCRONIUM_NUGGET, 1, 4), new AspectList().add(Aspect.LIGHT, 1).add(Aspect.METAL, 2).add(Aspect.CRYSTAL, 1).add(Aspect.CRAFT, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, 0), new AspectList().add(Aspect.WATER, 2).add(Aspect.CRYSTAL, 8));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, 1), new AspectList().add(Aspect.PLANT, 2).add(Aspect.CRYSTAL, 8));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, 2), new AspectList().add(Aspect.FIRE, 2).add(Aspect.CRYSTAL, 8));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, 3), new AspectList().add(Aspect.VOID, 2).add(Aspect.CRYSTAL, 8));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BLOCK, 1, 4), new AspectList().add(Aspect.LIGHT, 2).add(Aspect.CRYSTAL, 8));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BRICKS, 1, 0), new AspectList().add(Aspect.WATER, 2).add(Aspect.METAL, 4).add(Aspect.EARTH, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BRICKS, 1, 1), new AspectList().add(Aspect.PLANT, 2).add(Aspect.METAL, 4).add(Aspect.EARTH, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BRICKS, 1, 2), new AspectList().add(Aspect.FIRE, 2).add(Aspect.METAL, 4).add(Aspect.EARTH, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BRICKS, 1, 3), new AspectList().add(Aspect.VOID, 2).add(Aspect.METAL, 4).add(Aspect.EARTH, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_BRICKS, 1, 4), new AspectList().add(Aspect.LIGHT, 2).add(Aspect.METAL, 4).add(Aspect.EARTH, 4));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.INFUSED_BRICKS, 1, 0), new AspectList().add(Aspect.WATER, 2).add(Aspect.EARTH, 8).add(Aspect.CRYSTAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.INFUSED_BRICKS, 1, 1), new AspectList().add(Aspect.PLANT, 2).add(Aspect.EARTH, 8).add(Aspect.CRYSTAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.INFUSED_BRICKS, 1, 2), new AspectList().add(Aspect.FIRE, 2).add(Aspect.EARTH, 8).add(Aspect.CRYSTAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.INFUSED_BRICKS, 1, 3), new AspectList().add(Aspect.VOID, 2).add(Aspect.EARTH, 8).add(Aspect.CRYSTAL, 1));
        ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.INFUSED_BRICKS, 1, 4), new AspectList().add(Aspect.LIGHT, 2).add(Aspect.EARTH, 8).add(Aspect.CRYSTAL, 1));

        for(int i = 0; i < 16; i++) {
            ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_PLATE, 1, i), new AspectList().add(Aspect.METAL, 4).add(Aspect.CRAFT, 4).add(Aspect.CRYSTAL, 2));
            ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_PLATFORM, 1, i), new AspectList().add(Aspect.METAL, 4).add(Aspect.CRAFT, 4).add(Aspect.CRYSTAL, 2));
            ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_SHIELD, 1, i), new AspectList().add(Aspect.METAL, 4).add(Aspect.CRAFT, 4).add(Aspect.PROTECT, 4).add(Aspect.CRYSTAL, 2));
            ThaumcraftApi.registerObjectTag(new ItemStack(ModBlocks.XYCRONIUM_STRUCTURE, 1, i), new AspectList().add(Aspect.METAL, 4).add(Aspect.CRAFT, 4).add(Aspect.CRYSTAL, 2));
        }
    }

    @Override
    public void handleInit(FMLInitializationEvent event) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handlePreInitClient(FMLPreInitializationEvent event) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleInitClient(FMLInitializationEvent event) {

    }

}
