package de.kitsunealex.projectx.client.render;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCRenderState;
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
import de.kitsunealex.projectx.api.client.IAnimationHandler;
import de.kitsunealex.projectx.api.client.IColorProvider;
import de.kitsunealex.projectx.api.client.ITextureProvider;
import de.kitsunealex.projectx.util.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.model.IModelState;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RenderDefaultBlockGlow implements ICCBlockRenderer, IItemRenderer {

    public static final RenderDefaultBlockGlow INSTANCE = new RenderDefaultBlockGlow();
    private static CCModel MODEL = CCModel.quadModel(24).generateBlock(0, new Cuboid6(0D, 0D, 0D, 1D, 1D, 1D)).computeNormals();
    private static Cache<String, List<BakedQuad>> MODEL_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    @Override
    public boolean renderBlock(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
        BlockModelRenderer modelRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer();
        ITextureProvider provider = (ITextureProvider)state.getBlock();
        IAnimationHandler handler = (IAnimationHandler)state.getBlock();
        CCRenderState renderState = CCRenderState.instance();
        BlockRenderLayer layer = MinecraftForgeClient.getRenderLayer();
        String cacheKey = RenderUtils.getStateKey(state);

        if(layer == BlockRenderLayer.SOLID) {
            CCModel model = MODEL.copy().apply(new Translation(Vector3.fromBlockPos(pos)));
            renderState.reset();
            renderState.bind(buffer);

            for(int side = 0; side < 6; side++) {
                TextureAtlasSprite texture = handler.getAnimationTexture(world, pos, state, side);
                renderState.baseColour = handler.getAnimationColor(world, pos, state, side);
                renderState.brightness = handler.getAnimationBrightness(world, pos, state, side);
                renderState.pushLightmap();
                model.render(renderState, side * 4, side * 4 + 4, new IconTransformation(texture));
            }
        }
        else if(layer == BlockRenderLayer.CUTOUT) {
            if(MODEL_CACHE.getIfPresent(cacheKey) == null) {
                BakingVertexBuffer parentBuffer = BakingVertexBuffer.create();
                parentBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
                renderState.reset();
                renderState.bind(parentBuffer);

                for(int side = 0; side < 6; side++) {
                    TextureAtlasSprite texture = provider.getTexture(world, pos, state, side);

                    if(state.getBlock() instanceof IColorProvider) {
                        IColorProvider colorProvider = (IColorProvider)state.getBlock();
                        renderState.baseColour = colorProvider.getColorMultiplier(world, pos, state, side);
                    }

                    MODEL.render(renderState, side * 4, side * 4 + 4, new IconTransformation(texture));
                }

                parentBuffer.finishDrawing();
                MODEL_CACHE.put(cacheKey, parentBuffer.bake());
            }

            return RenderUtils.renderQuads(MODEL_CACHE.getIfPresent(cacheKey), world, pos, state, buffer);
        }

        return false;
    }

    @Override
    public void handleRenderBlockDamage(IBlockAccess world, BlockPos pos, IBlockState state, TextureAtlasSprite texture, BufferBuilder buffer) {
        CCModel model = MODEL.copy().apply(new Translation(Vector3.fromBlockPos(pos)));
        CCRenderState renderState = CCRenderState.instance();
        renderState.reset();
        renderState.bind(buffer);
        model.render(renderState, new IconTransformation(texture));
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
            TextureAtlasSprite texture = handler.getAnimationTexture(stack.getMetadata(), side);
            renderState.baseColour = handler.getAnimationColor(stack.getMetadata(), side);
            renderState.brightness = handler.getAnimationBrightness(stack.getMetadata(), side);
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
            TextureAtlasSprite texture = provider.getTexture(stack.getMetadata(), side);

            if(block instanceof IColorProvider) {
                IColorProvider colorProvider = (IColorProvider)block;
                renderState.baseColour = colorProvider.getColorMultiplier(stack.getMetadata(), side);
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
