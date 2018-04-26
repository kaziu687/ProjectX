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
import codechicken.lib.vec.Vertex5;
import codechicken.lib.vec.uv.IconTransformation;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.kitsunealex.projectx.api.client.ICTMBlock;
import de.kitsunealex.projectx.api.client.IColorProvider;
import de.kitsunealex.projectx.api.client.ITextureProvider;
import de.kitsunealex.projectx.util.ModelUtils;
import de.kitsunealex.projectx.util.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SideOnly(Side.CLIENT)
public class RenderDefaultBlock implements ICCBlockRenderer, IItemRenderer {

    public static final RenderDefaultBlock INSTANCE = new RenderDefaultBlock();
    private static CCModel MODEL = CCModel.quadModel(24).generateBlock(0, new Cuboid6(0D, 0D, 0D, 1D, 1D, 1D)).computeNormals();
    private static Cache<String, List<BakedQuad>> MODEL_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    @Override
    public boolean renderBlock(IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
        String cacheKey = RenderUtils.getStateKey(state);
        ITextureProvider provider = (ITextureProvider)state.getBlock();
        CCRenderState renderState = CCRenderState.instance();
        BakingVertexBuffer parentBuffer = BakingVertexBuffer.create();

        if(state.getBlock() instanceof ICTMBlock) {
            ICTMBlock block = (ICTMBlock)state.getBlock();
            ConnectedRenderContext context = ConnectedRenderContext.getInstance();
            renderState.reset();
            parentBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
            renderState.bind(parentBuffer);
            context.reset();
            context.setWorld(world);
            context.setMatchState(state);

            for(int side = 0; side < 6; side++) {
                if(state.shouldSideBeRendered(world, pos, EnumFacing.getFront(side))) {
                    TextureAtlasSprite[] textures = block.getConnectedTexture(world, pos, state, side);
                    List<Vertex5> vertices = context.renderSide(Vector3.fromBlockPos(pos), textures, EnumFacing.getFront(side));
                    CCModel sideModel = CCModel.newModel(GL11.GL_QUADS, vertices.size());
                    sideModel.verts = vertices.toArray(new Vertex5[vertices.size()]);

                    if(state.getBlock() instanceof IColorProvider) {
                        IColorProvider colorProvider = (IColorProvider)state.getBlock();
                        renderState.baseColour = colorProvider.getColorMultiplier(world, pos, state, side);
                    }

                    sideModel.computeNormals().render(renderState);
                }
            }

            parentBuffer.finishDrawing();
            return RenderUtils.renderQuads(parentBuffer.bake(), world, pos, state, buffer);
        }
        else {
            if(MODEL_CACHE.getIfPresent(cacheKey) == null) {
                renderState.reset();
                parentBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
                renderState.bind(parentBuffer);

                for(int side = 0; side < 6; side++) {
                    if(state.shouldSideBeRendered(world, pos, EnumFacing.getFront(side))) {
                        TextureAtlasSprite texture = provider.getTexture(world, pos, state, side);

                        if(state.getBlock() instanceof IColorProvider) {
                            IColorProvider colorProvider = (IColorProvider)state.getBlock();
                            renderState.baseColour = colorProvider.getColorMultiplier(world, pos, state, side);
                        }

                        MODEL.render(renderState, side * 4, side * 4 + 4, new IconTransformation(texture));
                    }
                }

                parentBuffer.finishDrawing();
                MODEL_CACHE.put(cacheKey, parentBuffer.bake());
            }

            return RenderUtils.renderQuads(MODEL_CACHE.getIfPresent(cacheKey), world, pos, state, buffer);
        }
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
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        CCRenderState renderState = CCRenderState.instance();
        GlStateManager.pushMatrix();
        renderState.reset();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
        renderState.bind(buffer);

        for(int side = 0; side < 6; side++) {
            TextureAtlasSprite texture = provider.getTexture(stack.getMetadata(), side);

            if(block instanceof IColorProvider) {
                IColorProvider colorProvider = (IColorProvider)block;
                renderState.baseColour = colorProvider.getColorMultiplier(stack.getMetadata(), side);
                MODEL.render(renderState, side * 4, side * 4 + 4, new IconTransformation(texture));
            }
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
