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

import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.util.TransformUtils;
import codechicken.lib.vec.Vector3;
import de.kitsunealex.projectx.api.power.EnumCoreType;
import de.kitsunealex.projectx.api.power.EnumPowerClass;
import de.kitsunealex.projectx.client.render.RenderPowerCore;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPowerCoreItem implements IItemRenderer {

    public static final RenderPowerCoreItem INSTANCE = new RenderPowerCoreItem();

    @Override
    public void renderItem(ItemStack stack, ItemCameraTransforms.TransformType transformType) {
        for(int i = 0; i < EnumCoreType.values().length; i++) {
            EnumCoreType coreType = EnumCoreType.values()[i];

            for(int j = 0; j < EnumPowerClass.values().length; j++) {
                EnumPowerClass powerClass = EnumPowerClass.values()[j];

                if(stack.getMetadata() == j + EnumPowerClass.values().length * i) {
                    RenderPowerCore.getInstance().renderPowerCore(Vector3.center, powerClass, coreType);
                }
            }
        }
    }

    @Override
    public IModelState getTransforms() {
        return TransformUtils.DEFAULT_ITEM;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

}
