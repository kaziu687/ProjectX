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

package de.kitsunealex.projectx.block;

import codechicken.lib.render.item.IItemRenderer;
import de.kitsunealex.projectx.client.IAnimationHandler;
import de.kitsunealex.projectx.client.render.block.RenderDefaultBlockGlow;
import de.kitsunealex.projectx.util.Constants;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockAnimationHandler<T extends TileEntity> extends BlockBase<T> implements IAnimationHandler {

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite texture;

    public BlockAnimationHandler(String blockName, Material material) {
        super(blockName, material);
    }

    public BlockAnimationHandler(String blockName, Material material, Class<? extends ItemBlock> itemBlock) {
        super(blockName, material, itemBlock);
    }

    public BlockAnimationHandler(String blockName, Material material, MapColor mapColor) {
        super(blockName, material, mapColor);
    }

    public BlockAnimationHandler(String blockName, Material material, MapColor mapColor, Class<? extends ItemBlock> itemBlock) {
        super(blockName, material, mapColor, itemBlock);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean canEntitySpawn(IBlockState state, Entity entity) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {
        String path = String.format("blocks/%s", blockName);
        texture = map.registerSprite(new ResourceLocation(Constants.MODID, path));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta, int side) {
        return texture;
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return RenderDefaultBlockGlow.RENDER_TYPE;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer() {
        return RenderDefaultBlockGlow.INSTANCE;
    }

}
