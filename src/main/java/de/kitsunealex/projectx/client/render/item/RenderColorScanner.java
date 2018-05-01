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

package de.kitsunealex.projectx.client.render.item;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.lib.vec.uv.IconTransformation;
import codechicken.lib.vec.uv.UVTranslation;
import de.kitsunealex.projectx.ProjectX;
import de.kitsunealex.projectx.client.ITextureProvider;
import de.kitsunealex.projectx.util.EnumXycroniumColor;
import de.kitsunealex.projectx.util.MathUtils;
import de.kitsunealex.projectx.util.ModelUtils;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderColorScanner implements IItemRenderer {

    public static final RenderColorScanner INSTANCE = new RenderColorScanner();
    private static CCModel[] MODEL;

    static {
        Cuboid6[] bounds = {
                //base
                new Cuboid6(5D, 3D, 7D, 11D, 11D, 9D),
                //probe 1
                new Cuboid6(9.5D, 10D, 7.75D, 10D, 12.75D, 8.25D),
                //probe 2
                new Cuboid6(7.75D, 11D, 7.75D, 8.25D, 12.5D, 8.25D),
                //probe 3
                new Cuboid6(6D, 10D, 7.75D, 6.5D, 12.75D, 8.25D),
                //frames
                new Cuboid6(5D, 10.5D, 6.75D, 11D, 11D, 7D),
                new Cuboid6(5D, 7D, 6.75D, 11D, 7.5D, 7D),
                new Cuboid6(10.5D, 7.5D, 6.75D, 11D, 10.5D, 7D),
                new Cuboid6(5D, 7.5D, 6.75D, 5.5D, 10.5D, 7D),
                //preview
                new Cuboid6(6D, 4D, 6.75D, 10D, 6D, 7D)
        };

        MODEL = ModelUtils.makeModel(MathUtils.divide(bounds, 16D));
        ModelUtils.rotate(MODEL[1], MathUtils.Z, 22.5D, new Vector3(9.75D, 11D, 8D));
        ModelUtils.rotate(MODEL[3], MathUtils.Z, -22.5D, new Vector3(6.25D, 11D, 8D));
    }

    @Override
    public void renderItem(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        ITextureProvider provider = (ITextureProvider)stack.getItem();
        CCRenderState renderState = CCRenderState.instance();
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5D, 0.5D, 0.5D);
        GlStateManager.rotate(180F, 0F, 1F, 0F);
        GlStateManager.translate(-0.5D, -0.5D, -0.5D);
        renderState.reset();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
        renderState.bind(buffer);

        for(int i = 0; i < MODEL.length; i++) {
            if(i != 1 && i != 2 && i != 3 && i != 8) {
                for(EnumFacing side : EnumFacing.VALUES) {
                    TextureAtlasSprite texture = TextureUtils.getMissingSprite();

                    if(side == EnumFacing.NORTH) {
                        texture = provider.getTexture(stack, 0);
                    }
                    else if(side == EnumFacing.SOUTH) {
                        texture = provider.getTexture(stack, 1);
                    }
                    else if(side == EnumFacing.EAST || side == EnumFacing.WEST) {
                        texture = provider.getTexture(stack, 2);
                    }
                    else {
                        texture = provider.getTexture(stack, 3);
                    }

                    MODEL[i].render(renderState, side.getIndex() * 4, side.getIndex() * 4 + 4, new IconTransformation(texture));
                }
            }
            else {
                int lb = (int) OpenGlHelper.lastBrightnessY << 16 | (int)OpenGlHelper.lastBrightnessX;
                GlStateManager.disableLighting();
                renderState.brightness = 220;
                renderState.pushLightmap();

                if(i == 8) {

                }
                else if(i == 1 || i == 2 || i == 3) {
                    MODEL[i].setColour(EnumXycroniumColor.values()[i - 1].getColorRGBA());
                    MODEL[i].render(renderState, new IconTransformation(ProjectX.PROXY.getAnimation()));
                }

                renderState.brightness = lb;
                renderState.pushLightmap();
                GlStateManager.enableLighting();
            }
        }

        Tessellator.getInstance().draw();
        GlStateManager.popMatrix();
    }

    @Override
    public IModelState getTransforms() {
        return TransformUtils.DEFAULT_ITEM;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

}
