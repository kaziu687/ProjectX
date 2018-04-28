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

package de.kitsunealex.projectx.util;

import codechicken.lib.render.CCModel;
import codechicken.lib.vec.Transformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class ModelUtils {

    public static CCModel[] copyAndTransform(CCModel[] model, Transformation... transformations) {
        return Arrays.stream(model).map(m -> copyAndTransform(m, transformations)).toArray(CCModel[]::new);
    }

    public static CCModel copyAndTransform(CCModel model, Transformation... transformations) {
        CCModel newModel = model.copy();
        Arrays.stream(transformations).forEach(newModel::apply);
        return newModel;
    }

}
