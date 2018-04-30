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

package de.kitsunealex.projectx.proxy;

import codechicken.lib.packet.PacketCustom;
import de.kitsunealex.projectx.init.*;
import de.kitsunealex.projectx.network.ServerPacketHandler;
import de.kitsunealex.projectx.recipe.RecipeHandler;
import de.kitsunealex.projectx.util.Constants;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void handlePreInit(FMLPreInitializationEvent event) {
        ModConfig.loadConfig(event.getSuggestedConfigurationFile());
        ModBlocks.registerBlocks();
        ModItems.registerItems();
    }

    public void handleInit(FMLInitializationEvent event) {
        ModBlocks.registerTileEntities();
        ModOreDictionary.registerEntries();
        ModCrafting.registerRecipes();
        RecipeHandler.registerRecipes();
        ModWorldGen.registerWorldGen();
        PacketCustom.assignHandler(Constants.MODID, new ServerPacketHandler());
    }

    public void handlePostInit(FMLPostInitializationEvent event) {

    }

    public void registerBlockRenderer(Block block) {

    }

    public void registerItemRenderer(Item item) {

    }

    public TextureAtlasSprite getAnimation() {
        return null;
    }

}
