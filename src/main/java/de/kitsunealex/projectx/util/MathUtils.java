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

import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;

import java.util.Arrays;

public class MathUtils {

    public static final Cuboid6 ZERO = new Cuboid6(0D, 0D, 0D, 0D, 0D, 0D);
    public static final Cuboid6 FULL = new Cuboid6(0D, 0D, 0D, 1D, 1D, 1D);
    public static final Vector3 X = new Vector3(1D, 0D, 0D);
    public static final Vector3 Y = new Vector3(0D, 1D, 0D);
    public static final Vector3 Z = new Vector3(0D, 0D, 1D);

    public static Cuboid6[] divide(Cuboid6[] cuboids, double d) {
        return Arrays.stream(cuboids).map(c -> divide(c, d)).toArray(Cuboid6[]::new);
    }

    public static Cuboid6 divide(Cuboid6 cuboid, double d) {
        Vector3 min = cuboid.min.copy().divide(d);
        Vector3 max = cuboid.max.copy().divide(d);
        return new Cuboid6(min, max);
    }

}
