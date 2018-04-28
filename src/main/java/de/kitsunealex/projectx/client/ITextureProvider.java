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

package de.kitsunealex.projectx.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ITextureProvider {

    @SideOnly(Side.CLIENT)
    TextureAtlasSprite getTexture(int meta, int side);

    @SideOnly(Side.CLIENT)
    default TextureAtlasSprite getTexture(ItemStack stack, int side) {
        return getTexture(stack.getMetadata(), side);
    }

    @SideOnly(Side.CLIENT)
    default TextureAtlasSprite getTexture(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return getTexture(state.getBlock().getMetaFromState(state), side);
    }

}
