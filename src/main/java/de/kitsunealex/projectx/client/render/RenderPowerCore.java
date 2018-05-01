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

package de.kitsunealex.projectx.client.render;

import codechicken.lib.colour.Colour;
import codechicken.lib.util.ClientUtils;
import codechicken.lib.vec.Vector3;
import de.kitsunealex.projectx.api.power.EnumCoreType;
import de.kitsunealex.projectx.api.power.EnumPowerClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPowerCore {

    private static final ThreadLocal<RenderPowerCore> INSTANCES = ThreadLocal.withInitial(RenderPowerCore::new);

    public void renderPowerCore(Vector3 position, EnumPowerClass powerClass, EnumCoreType coreType) {
        RenderTruncatedIcosahedron renderer = RenderTruncatedIcosahedron.getInstance();
        Colour colorCore = powerClass.getColor();
        Colour colorShell = coreType.getColor();
        GlStateManager.pushMatrix();
        position.translation().glApply();

        if(!Minecraft.getMinecraft().isGamePaused()) {
            GlStateManager.rotate((float)(-ClientUtils.getRenderTime() * 10F) * 0.5F, 0F, 1F, 0F);
            GlStateManager.rotate((float)(-ClientUtils.getRenderTime() * 2.5F) * 0.5F, 0F, 0F, 1F);
            GlStateManager.rotate((float)(-ClientUtils.getRenderTime() * -1F) * 0.5F, 1F, 0F, 0F);
        }

        renderer.render(0.85D, colorShell.copy().multiplyC(0.7D), colorShell, EnumHedronTexture.SPACE);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        position.translation().glApply();

        if(!Minecraft.getMinecraft().isGamePaused()) {
            GlStateManager.rotate((float)(ClientUtils.getRenderTime() * 10F) * 0.5F, 0F, 1F, 0F);
            GlStateManager.rotate((float)(ClientUtils.getRenderTime() * 2.5F) * 0.5F, 0F, 0F, 1F);
            GlStateManager.rotate((float)(ClientUtils.getRenderTime() * -1F) * 0.5F, 1F, 0F, 0F);
        }

        renderer.render(0.64D, colorCore.copy().multiplyC(0.7D), colorCore, EnumHedronTexture.FILL);
        GlStateManager.popMatrix();
    }

    public static RenderPowerCore getInstance() {
        return INSTANCES.get();
    }

}
