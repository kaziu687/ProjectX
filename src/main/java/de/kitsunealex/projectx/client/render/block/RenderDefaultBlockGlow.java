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

package de.kitsunealex.projectx.client.render.block;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.block.BlockRenderingRegistry;
import codechicken.lib.render.block.ICCBlockRenderer;
import codechicken.lib.render.buffer.BakingVertexBuffer;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Translation;
import codechicken.lib.vec.Vector3;
import codechicken.lib.vec.uv.IconTransformation;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.kitsunealex.projectx.client.IAnimationHandler;
import de.kitsunealex.projectx.client.IColorProvider;
import de.kitsunealex.projectx.client.ITextureProvider;
import de.kitsunealex.projectx.util.ModelUtils;
import de.kitsunealex.projectx.util.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.model.IModelState;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RenderDefaultBlockGlow implements ICCBlockRenderer, IItemRenderer {

    public static final RenderDefaultBlockGlow INSTANCE = new RenderDefaultBlockGlow();
    public static EnumBlockRenderType RENDER_TYPE;
    private static CCModel MODEL = CCModel.quadModel(24).generateBlock(0, new Cuboid6(0D, 0D, 0D, 1D, 1D, 1D)).computeNormals();
    private static Cache<String, List<BakedQuad>> MODEL_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    static {
        RENDER_TYPE = BlockRenderingRegistry.createRenderType("default_block_glow");
        BlockRenderingRegistry.registerRenderer(RENDER_TYPE, INSTANCE);
    }

    @Override
    public boolean renderBlock(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
        CCRenderState renderState = CCRenderState.instance();
        BakingVertexBuffer bakingBuffer = BakingVertexBuffer.create();
        BlockRenderLayer layer = MinecraftForgeClient.getRenderLayer();

        if(layer == BlockRenderLayer.SOLID) {
            return renderAnimation(world, pos, state, buffer, renderState);
        }
        else if(layer == BlockRenderLayer.CUTOUT) {
            return renderOverlay(world, pos, state, buffer, bakingBuffer, renderState);
        }

        return false;
    }

    private boolean renderAnimation(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer, CCRenderState renderState) {
        IAnimationHandler handler = (IAnimationHandler)state.getBlock();
        CCModel model = ModelUtils.copyAndTransform(MODEL, new Translation(Vector3.fromBlockPos(pos)));
        renderState.reset();
        renderState.bind(buffer);

        for(EnumFacing side : EnumFacing.VALUES) {
            TextureAtlasSprite texture = handler.getAnimationTexture(world, pos, state, side.getIndex());
            renderState.baseColour = handler.getAnimationColor(world, pos, state, side.getIndex());
            renderState.brightness = handler.getAnimationBrightness(world, pos, state, side.getIndex());
            model.render(renderState, side.getIndex() * 4, side.getIndex() * 4 + 4, new IconTransformation(texture));
        }

        return false;
    }

    private boolean renderOverlay(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer, BakingVertexBuffer bakingBuffer, CCRenderState renderState) {
        ITextureProvider provider = (ITextureProvider)state.getBlock();
        String cacheKey = RenderUtils.getStateKey(state);

        if(MODEL_CACHE.getIfPresent(cacheKey) == null) {
            bakingBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
            renderState.reset();
            renderState.bind(bakingBuffer);

            for(int side = 0; side < 6; side++) {
                TextureAtlasSprite texture = provider.getTexture(world, pos, state, side);

                if(state.getBlock() instanceof IColorProvider) {
                    IColorProvider colorProvider = (IColorProvider)state.getBlock();
                    renderState.baseColour = colorProvider.getColorMultiplier(world, pos, state, side);
                }

                MODEL.render(renderState, side * 4, side * 4 + 4, new IconTransformation(texture));
            }

            bakingBuffer.finishDrawing();
            MODEL_CACHE.put(cacheKey, bakingBuffer.bake());
        }

        return RenderUtils.renderQuads(MODEL_CACHE.getIfPresent(cacheKey), world, pos, state, buffer);
    }

    @Override
    public void handleRenderBlockDamage(IBlockAccess world, BlockPos pos, IBlockState state, TextureAtlasSprite texture, BufferBuilder buffer) {
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.bind(buffer);
        ModelUtils.copyAndTransform(MODEL, new Translation(Vector3.fromBlockPos(pos))).render(renderState, new IconTransformation(texture));
    }

    @Override
    public void renderBrightness(IBlockState state, float brightness) {}

    @SuppressWarnings("deprecation")
    @Override
    public void registerTextures(TextureMap map) {}

    @Override
    public void renderItem(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        Block block = Block.getBlockFromItem(stack.getItem());
        ITextureProvider provider = (ITextureProvider)block;
        IAnimationHandler handler = (IAnimationHandler)block;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        CCRenderState renderState = CCRenderState.instance();
        int lb = (int)OpenGlHelper.lastBrightnessY << 16 | (int)OpenGlHelper.lastBrightnessX;
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
        renderState.reset();
        renderState.bind(buffer);

        for(int side = 0; side < 6; side++) {
            TextureAtlasSprite texture = handler.getAnimationTexture(stack, side);
            renderState.baseColour = handler.getAnimationColor(stack, side);
            renderState.brightness = handler.getAnimationBrightness(stack, side);
            renderState.pushLightmap();
            MODEL.render(renderState, side * 4, side * 4 + 4, new IconTransformation(texture));
        }

        Tessellator.getInstance().draw();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
        renderState.reset();
        renderState.bind(buffer);
        renderState.brightness = lb;
        renderState.pushLightmap();

        for(int side = 0; side < 6; side++) {
            TextureAtlasSprite texture = provider.getTexture(stack, side);

            if(block instanceof IColorProvider) {
                IColorProvider colorProvider = (IColorProvider)block;
                renderState.baseColour = colorProvider.getColorMultiplier(stack, side);
            }

            MODEL.render(renderState, side * 4, side * 4 + 4, new IconTransformation(texture));
        }

        Tessellator.getInstance().draw();
        GlStateManager.popMatrix();
    }

    @Override
    public IModelState getTransforms() {
        return TransformUtils.DEFAULT_BLOCK;
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
