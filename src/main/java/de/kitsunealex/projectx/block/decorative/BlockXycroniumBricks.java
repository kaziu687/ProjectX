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

package de.kitsunealex.projectx.block.decorative;

import de.kitsunealex.projectx.ProjectX;
import de.kitsunealex.projectx.block.BlockAnimationHandler;
import de.kitsunealex.projectx.client.IColorProvider;
import de.kitsunealex.projectx.util.EnumXycroniumColor;
import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public class BlockXycroniumBricks extends BlockAnimationHandler implements ISubtypeHolder, IColorProvider {

    public BlockXycroniumBricks() {
        super("xycronium_bricks", Material.ROCK);
        setHardness(1.4F);
        setResistance(1.5F);
    }

    @Override
    public String[] getSubNames() {
        return Arrays.stream(EnumXycroniumColor.values()).map(EnumXycroniumColor::getName).toArray(String[]::new);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getAnimationTexture(int meta, int side) {
        return ProjectX.PROXY.getAnimation();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationColor(int meta, int side) {
        return EnumXycroniumColor.values()[meta].getColorRGBA();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationBrightness(int meta, int side) {
        return 220;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorMultiplier(int meta, int side) {
        return EnumXycroniumColor.values()[meta].getColor().copy().multiplyC(0.42D).rgba();
    }

}
