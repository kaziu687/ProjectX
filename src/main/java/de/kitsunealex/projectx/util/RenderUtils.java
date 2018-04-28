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

package de.kitsunealex.projectx.util;

import de.kitsunealex.projectx.client.render.SimpleBakedModel;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class RenderUtils {

    public static boolean renderQuads(List<BakedQuad> quads, IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
        boolean ambientOcclusion = Minecraft.isAmbientOcclusionEnabled();
        BlockModelRenderer renderer = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer();
        IBakedModel model = new SimpleBakedModel(quads);
        long random = MathHelper.getPositionRandom(new Vec3i(pos.getX(), pos.getY(), pos.getZ()));

        if(ambientOcclusion) {
            return renderer.renderModelSmooth(world, model, state, pos, buffer, true, random);
        }
        else {
            return renderer.renderModelFlat(world, model, state, pos, buffer, true, random);
        }
    }

    public static String getStateKey(IBlockState state) {
        StringBuilder builder = new StringBuilder();
        builder.append(state.getBlock().getRegistryName().toString());

        for(Map.Entry<IProperty<?>, Comparable<?>> property : state.getProperties().entrySet()) {
            builder.append('|');
            builder.append(property.getKey().getName());
            builder.append('=');
            builder.append(property.getValue());
        }

        return builder.toString();
    }

}
