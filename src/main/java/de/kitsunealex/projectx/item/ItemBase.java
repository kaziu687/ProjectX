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

package de.kitsunealex.projectx.item;

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.texture.TextureUtils;
import de.kitsunealex.projectx.ProjectX;
import de.kitsunealex.projectx.client.IItemRenderProvider;
import de.kitsunealex.projectx.client.ITextureProvider;
import de.kitsunealex.projectx.client.render.item.RenderDefaultItem;
import de.kitsunealex.projectx.util.Constants;
import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item implements TextureUtils.IIconRegister, ITextureProvider, IItemRenderProvider {

    protected String itemName;
    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite[] texture;

    public ItemBase(String itemName) {
        this.itemName = itemName;
        setRegistryName(Constants.MODID, itemName);
        setUnlocalizedName(String.format("%s.%s", Constants.MODID, itemName));
        setCreativeTab(ProjectX.CREATIVE_TAB);
        setHasSubtypes(this instanceof ISubtypeHolder);
        ForgeRegistries.ITEMS.register(this);
        ProjectX.PROXY.registerItemRenderer(this);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab == getCreativeTab()) {
            if(this instanceof ISubtypeHolder) {
                String[] subNames = ((ISubtypeHolder)this).getSubNames();

                for(int i = 0; i < subNames.length; i++) {
                    items.add(new ItemStack(this, 1, i));
                }
            }
            else {
                items.add(new ItemStack(this, 1, 0));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getUnlocalizedName(ItemStack stack) {
        if(this instanceof ISubtypeHolder) {
            String[] subNames = ((ISubtypeHolder)this).getSubNames();
            return String.format("%s.%s", getUnlocalizedName(), subNames[stack.getMetadata()]);
        }
        else {
            return getUnlocalizedName();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {
        if(this instanceof ISubtypeHolder) {
            String[] subNames = ((ISubtypeHolder)this).getSubNames();
            texture = new TextureAtlasSprite[subNames.length];

            for(int i = 0; i < subNames.length; i++) {
                String path = String.format("items/%s_%s", itemName, subNames[i]);
                texture[i] = map.registerSprite(new ResourceLocation(Constants.MODID, path));
            }
        }
        else {
            texture = new TextureAtlasSprite[1];
            String path = String.format("items/%s", itemName);
            texture[0] = map.registerSprite(new ResourceLocation(Constants.MODID, path));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta, int side) {
        return texture[meta];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer() {
        return RenderDefaultItem.DEFAULT_ITEM;
    }

}
