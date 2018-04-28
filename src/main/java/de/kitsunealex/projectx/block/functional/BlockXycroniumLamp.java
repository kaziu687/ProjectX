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

package de.kitsunealex.projectx.block.functional;

import codechicken.lib.colour.EnumColour;
import codechicken.lib.util.ItemNBTUtils;
import de.kitsunealex.projectx.ProjectX;
import de.kitsunealex.projectx.block.BlockAnimationHandler;
import de.kitsunealex.projectx.tile.TileEntityXycroniumLamp;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockXycroniumLamp extends BlockAnimationHandler<TileEntityXycroniumLamp> {

    private final boolean defaultState;

    public BlockXycroniumLamp(boolean defaultState) {
        super(String.format("xycronium_lamp%s", defaultState ? "_inverted" : ""), Material.GLASS);
        this.defaultState = defaultState;
        setHardness(1.2F);
        setResistance(1.5F);
        setDefaultState(blockState.getBaseState().withProperty(METADATA, defaultState ? 1 : 0));
    }

    @Nullable
    @Override
    public TileEntityXycroniumLamp createNewTileEntity(World world, int meta) {
        return new TileEntityXycroniumLamp();
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getMetaFromState(state) == 1 ? 255 : 0;
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        for(EnumColour color : EnumColour.values()) {
            ItemStack stack = new ItemStack(this, 1, 0);
            ItemNBTUtils.setInteger(stack, "color", color.rgba());
            items.add(stack);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getAnimationTexture(int meta, int side) {
        return ProjectX.PROXY.getAnimation();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationColor(int meta, int side) {
        return 0xFFFFFFFF;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationColor(ItemStack stack, int side) {
        return ItemNBTUtils.hasKey(stack, "color") ? ItemNBTUtils.getInteger(stack, "color") : super.getAnimationColor(stack, side);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationColor(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        if(world.getTileEntity(pos) != null) {
            TileEntityXycroniumLamp tile = (TileEntityXycroniumLamp)world.getTileEntity(pos);
            return tile.getColor();
        }
        else {
            return super.getAnimationColor(world, pos, state, side);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationBrightness(int meta, int side) {
        return defaultState ? 220 : 120;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationBrightness(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return getMetaFromState(state) == 1 ? 220 : 120;
    }

}
