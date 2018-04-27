package de.kitsunealex.projectx.util;

import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;

import java.util.Arrays;

public class MathUtils {

    public static final Cuboid6 ZERO = new Cuboid6(0D, 0D, 0D, 0D, 0D, 0D);
    public static final Cuboid6 FULL = new Cuboid6(0D, 0D, 0D, 1D, 1D, 1D);

    public static Cuboid6[] divide(Cuboid6[] cuboids, double d) {
        return Arrays.stream(cuboids).map(c -> divide(c, d)).toArray(Cuboid6[]::new);
    }

    public static Cuboid6 divide(Cuboid6 cuboid, double d) {
        Vector3 min = cuboid.min.copy().divide(d);
        Vector3 max = cuboid.max.copy().divide(d);
        return new Cuboid6(min, max);
    }

}
