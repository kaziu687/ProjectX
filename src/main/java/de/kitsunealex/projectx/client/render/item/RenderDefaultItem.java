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

import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.model.ItemQuadBakery;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.kitsunealex.projectx.client.IColorProvider;
import de.kitsunealex.projectx.client.IItemRenderProvider;
import de.kitsunealex.projectx.client.ITextureProvider;
import de.kitsunealex.projectx.client.render.SimpleBakedModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.concurrent.TimeUnit;

@SideOnly(Side.CLIENT)
public class RenderDefaultItem implements IItemRenderer {

    public static final RenderDefaultItem DEFAULT_ITEM = new RenderDefaultItem(TransformUtils.DEFAULT_ITEM);
    public static final RenderDefaultItem DEFAULT_HANDHELD_ROD = new RenderDefaultItem(TransformUtils.DEFAULT_HANDHELD_ROD);
    public static final RenderDefaultItem DEFAULT_TOOL = new RenderDefaultItem(TransformUtils.DEFAULT_TOOL);
    public static final RenderDefaultItem DEFAULT_BOW = new RenderDefaultItem(TransformUtils.DEFAULT_BOW);
    private static final Cache<String, IBakedModel> MODEL_CACHE = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();
    private IModelState transforms;

    public RenderDefaultItem(IModelState transforms) {
        this.transforms = transforms;
    }

    @Override
    public void renderItem(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        IItemRenderProvider renderProvider = (IItemRenderProvider)stack.getItem();
        ITextureProvider textureProvider = (ITextureProvider)stack.getItem();
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        String cacheKey = renderProvider.getRenderKey().apply(stack);

        if(MODEL_CACHE.getIfPresent(cacheKey) == null) {
            TextureAtlasSprite texture = textureProvider.getTexture(stack, 0);
            MODEL_CACHE.put(cacheKey, new SimpleBakedModel(ItemQuadBakery.bakeItem(texture)));
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5D, 0.5D, 0.5D);
        IBakedModel model = MODEL_CACHE.getIfPresent(cacheKey);

        if(stack.getItem() instanceof IColorProvider) {
            IColorProvider provider = (IColorProvider)stack.getItem();
            new ColourRGBA(provider.getColorMultiplier(stack, 0)).glColour();
        }

        renderItem.renderItem(stack, model);
        GlStateManager.popMatrix();
    }

    @Override
    public IModelState getTransforms() {
        return transforms;
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
