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

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.colour.EnumColour;
import codechicken.lib.util.ItemNBTUtils;
import codechicken.lib.util.ItemUtils;
import com.google.common.collect.Lists;
import de.kitsunealex.projectx.ProjectX;
import de.kitsunealex.projectx.block.BlockAnimationHandler;
import de.kitsunealex.projectx.tile.TileEntityXycroniumLamp;
import de.kitsunealex.projectx.util.IShiftDescription;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockXycroniumLamp extends BlockAnimationHandler<TileEntityXycroniumLamp> implements IShiftDescription {

    public BlockXycroniumLamp() {
        this("xycronium_lamp");
    }

    public BlockXycroniumLamp(String blockName) {
        super(blockName, Material.GLASS);
        setHardness(1.2F);
        setResistance(1.5F);
    }

    @Nullable
    @Override
    public TileEntityXycroniumLamp createNewTileEntity(World world, int meta) {
        return new TileEntityXycroniumLamp();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if(!world.isRemote) {
            if(world.getTileEntity(pos) != null && ItemNBTUtils.hasKey(stack, "color")) {
                TileEntityXycroniumLamp tile = (TileEntityXycroniumLamp)world.getTileEntity(pos);
                tile.setColor(ItemNBTUtils.getInteger(stack, "color"));
                tile.sendUpdatePacket(true);
            }
        }

        if(getMetaFromState(state) == 0 && world.isBlockIndirectlyGettingPowered(pos) > 0) {
            world.setBlockState(pos, state.withProperty(METADATA, 1), 3);
        }
        else if(getMetaFromState(state) == 1 && world.isBlockIndirectlyGettingPowered(pos) == 0) {
            world.setBlockState(pos, state.withProperty(METADATA, 0), 3);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos sourcePos) {
        if(getMetaFromState(state) == 0 && world.isBlockIndirectlyGettingPowered(pos) > 0) {
            world.setBlockState(pos, state.withProperty(METADATA, 1), 3);
        }
        else if(getMetaFromState(state) == 1 && world.isBlockIndirectlyGettingPowered(pos) == 0) {
            world.setBlockState(pos, state.withProperty(METADATA, 0), 3);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return Lists.newArrayList();
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        if(!world.isRemote && world.getTileEntity(pos) != null && !player.capabilities.isCreativeMode) {
            TileEntityXycroniumLamp tile = (TileEntityXycroniumLamp)world.getTileEntity(pos);
            ItemStack stack = new ItemStack(this, 1, 0);
            ItemNBTUtils.setInteger(stack, "color", tile.getColor());
            ItemUtils.dropItem(world, pos, stack);
        }
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
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        if(world.getTileEntity(pos) != null) {
            TileEntityXycroniumLamp tile = (TileEntityXycroniumLamp)world.getTileEntity(pos);
            ItemStack stack = new ItemStack(this, 1, 0);
            ItemNBTUtils.setInteger(stack, "color", tile.getColor());
            return stack;
        }
        else {
            return super.getPickBlock(state, target, world, pos, player);
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
        return meta == 1 ? 220 : 120;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDescription(ItemStack stack, List<String> lines, boolean isShiftDown) {
        if(isShiftDown) {
            Colour color;

            if(ItemNBTUtils.hasKey(stack, "color")) {
                color = new ColourRGBA(ItemNBTUtils.getInteger(stack, "color"));
            }
            else {
                color = new ColourRGBA(255, 255, 255, 255);
            }

            lines.add(TextFormatting.RED + "R: " + (color.r & 0xFF));
            lines.add(TextFormatting.GREEN + "G: " + (color.g & 0xFF));
            lines.add(TextFormatting.BLUE + "B: " + (color.b & 0xFF));
        }

        return !isShiftDown;
    }

}
