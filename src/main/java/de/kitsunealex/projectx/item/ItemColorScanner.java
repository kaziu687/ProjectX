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
import de.kitsunealex.projectx.client.render.item.RenderColorScanner;
import de.kitsunealex.projectx.util.Constants;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemColorScanner extends ItemBase {

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite[] texture;

    public ItemColorScanner() {
        super("color_scanner");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {
        texture = new TextureAtlasSprite[4];
        texture[0] = map.registerSprite(new ResourceLocation(Constants.MODID, String.format("items/%s/%s_n", itemName, itemName)));
        texture[1] = map.registerSprite(new ResourceLocation(Constants.MODID, String.format("items/%s/%s_s", itemName, itemName)));
        texture[2] = map.registerSprite(new ResourceLocation(Constants.MODID, String.format("items/%s/%s_ew", itemName, itemName)));
        texture[3] = map.registerSprite(new ResourceLocation(Constants.MODID, String.format("items/%s/%s_ud", itemName, itemName)));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta, int side) {
        return texture[side];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer() {
        return RenderColorScanner.INSTANCE;
    }

}
