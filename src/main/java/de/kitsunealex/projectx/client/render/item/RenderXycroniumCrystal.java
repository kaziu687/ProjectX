package de.kitsunealex.projectx.client.render.item;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCModelLibrary;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.vec.uv.IconTransformation;
import de.kitsunealex.projectx.client.IAnimationHandler;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderXycroniumCrystal implements IItemRenderer {

    public static final RenderXycroniumCrystal INSTANCE = new RenderXycroniumCrystal();
    private static CCModel MODEL = CCModelLibrary.icosahedron7.copy().computeNormals();

    @Override
    public void renderItem(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        IAnimationHandler handler = (IAnimationHandler)stack.getItem();
        CCRenderState renderState = CCRenderState.instance();
        TextureAtlasSprite texture = handler.getAnimationTexture(stack, 0);
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.translate(0.5D, 0.5D, 0.5D);
        GlStateManager.scale(0.075D, 0.2D, 0.075D);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);
        renderState.reset();
        renderState.bind(buffer);
        renderState.baseColour = handler.getAnimationColor(stack, 0);
        renderState.brightness = handler.getAnimationBrightness(stack, 0);
        renderState.pushLightmap();
        MODEL.copy().render(renderState, new IconTransformation(texture));
        Tessellator.getInstance().draw();
        GlStateManager.enableLighting();
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
        return false;
    }

}
