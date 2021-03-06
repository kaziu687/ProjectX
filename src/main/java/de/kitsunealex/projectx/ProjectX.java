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

package de.kitsunealex.projectx;

import de.kitsunealex.projectx.init.ModBlocks;
import de.kitsunealex.projectx.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

import static de.kitsunealex.projectx.util.Constants.*;

@Mod(modid = MODID, name = NAME, version = VERSION, useMetadata = true)
public class ProjectX {

    @Mod.Instance(MODID)
    public static ProjectX INSTANCE;
    @SidedProxy(clientSide = CSIDE, serverSide = SSIDE)
    public static CommonProxy PROXY;
    public static Logger LOGGER;

    public static CreativeTabs CREATIVE_TAB = new CreativeTabs("projectx.name") {
        @Override
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.XYCRONIUM_BRICKS, 1, 0);
        }
    };

    @Mod.EventHandler
    public void handlePreInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        PROXY.handlePreInit(event);
    }

    @Mod.EventHandler
    public void handleInit(FMLInitializationEvent event) {
        PROXY.handleInit(event);
    }

    @Mod.EventHandler
    public void handlePostInit(FMLPostInitializationEvent event) {
        PROXY.handlePostInit(event);
    }

}
