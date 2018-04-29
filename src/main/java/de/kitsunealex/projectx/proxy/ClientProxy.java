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

import codechicken.lib.model.DummyBakedModel;
import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.packet.PacketCustom;
import codechicken.lib.texture.TextureUtils;
import de.kitsunealex.projectx.client.AnimatedTexture;
import de.kitsunealex.projectx.client.IItemRenderProvider;
import de.kitsunealex.projectx.network.ClientPacketHandler;
import de.kitsunealex.projectx.util.Constants;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    private TextureAtlasSprite animation;

    @Override
    public void handlePreInit(FMLPreInitializationEvent event) {
        super.handlePreInit(event);
        animation = new AnimatedTexture(64).texture;
    }

    @Override
    public void handleInit(FMLInitializationEvent event) {
        super.handleInit(event);
        PacketCustom.assignHandler(Constants.MODID, new ClientPacketHandler());
    }

    @Override
    public void handlePostInit(FMLPostInitializationEvent event) {
        super.handlePostInit(event);
    }

    @Override
    public void registerBlockRenderer(Block block) {
        super.registerBlockRenderer(block);

        if(block instanceof TextureUtils.IIconRegister) {
            TextureUtils.addIconRegister((TextureUtils.IIconRegister)block);
        }

        StateMap.Builder builder = new StateMap.Builder();
        block.getBlockState().getProperties().forEach(builder::ignore);
        ModelLoader.setCustomStateMapper(block, builder.build());
        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "normal");
        ModelRegistryHelper.register(location, new DummyBakedModel());

        if(block instanceof IItemRenderProvider) {
            Item item = Item.getItemFromBlock(block);
            IItemRenderProvider provider = (IItemRenderProvider)block;
            ModelResourceLocation itemLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
            ModelLoader.setCustomMeshDefinition(item, stack -> itemLocation);
            ModelRegistryHelper.registerItemRenderer(item, provider.getItemRenderer());
        }
    }

    @Override
    public void registerItemRenderer(Item item) {
        super.registerItemRenderer(item);

        if(item instanceof TextureUtils.IIconRegister) {
            TextureUtils.addIconRegister((TextureUtils.IIconRegister)item);
        }

        if(item instanceof IItemRenderProvider) {
            IItemRenderProvider provider = (IItemRenderProvider)item;
            ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
            ModelLoader.setCustomMeshDefinition(item, stack -> location);
            ModelRegistryHelper.registerItemRenderer(item, provider.getItemRenderer());
        }
    }

    @Override
    public TextureAtlasSprite getAnimation() {
        return animation;
    }

}
