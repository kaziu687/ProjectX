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

package de.kitsunealex.projectx.item;

import codechicken.lib.render.item.IItemRenderer;
import de.kitsunealex.projectx.api.power.EnumCoreType;
import de.kitsunealex.projectx.api.power.EnumPowerClass;
import de.kitsunealex.projectx.client.render.item.RenderPowerCoreItem;
import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPowerCore extends ItemBase implements ISubtypeHolder {

    public ItemPowerCore() {
        super("power_core");
    }

    @Override
    public String[] getSubNames() {
        String[] names = new String[EnumCoreType.values().length * EnumPowerClass.values().length];

        for(int i = 0; i < EnumCoreType.values().length; i++) {
            EnumCoreType coreType = EnumCoreType.values()[i];

            for(int j = 0; j < EnumPowerClass.values().length; j++) {
                EnumPowerClass powerClass = EnumPowerClass.values()[j];
                names[j + EnumPowerClass.values().length * i] = String.format("%s_%s", coreType.getName(), powerClass.getName());
            }
        }

        return names;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {}

    @Override
    @SideOnly(Side.CLIENT)
    public String getUnlocalizedName(ItemStack stack) {
        return getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer() {
        return RenderPowerCoreItem.INSTANCE;
    }

}
