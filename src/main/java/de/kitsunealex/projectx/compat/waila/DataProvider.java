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

package de.kitsunealex.projectx.compat.waila;

import codechicken.lib.colour.Colour;
import com.google.common.collect.Lists;
import de.kitsunealex.projectx.tile.TileEntityXycroniumLamp;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import java.util.List;

public class DataProvider implements IWailaDataProvider {

    @Nonnull
    @Override
    public List<String> getWailaBody(ItemStack stack, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if(accessor.getTileEntity() instanceof TileEntityXycroniumLamp) {
            TileEntityXycroniumLamp tile = (TileEntityXycroniumLamp)accessor.getTileEntity();
            int[] color = Colour.unpack(tile.getColor());
            tooltip.add(TextFormatting.RED + "R: " + color[0]);
            tooltip.add(TextFormatting.GREEN + "G: " + color[1]);
            tooltip.add(TextFormatting.BLUE + "B: " + color[2]);
        }

        return Lists.newArrayList();
    }

}
