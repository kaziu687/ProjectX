package de.kitsunealex.projectx.util;

import codechicken.lib.render.CCModel;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Transformation;
import codechicken.lib.vec.Vector3;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class ModelUtils {

    public static Cuboid6[] divide(Cuboid6[] cuboids, double d) {
        return Arrays.stream(cuboids).map(c -> divide(c, d)).toArray(Cuboid6[]::new);
    }

    public static Cuboid6 divide(Cuboid6 cuboid, double d) {
        Vector3 min = cuboid.min.copy().divide(d);
        Vector3 max = cuboid.max.copy().divide(d);
        return new Cuboid6(min, max);
    }

    public static CCModel[] copyAndTransform(CCModel[] model, Transformation... transformations) {
        return Arrays.stream(model).map(m -> copyAndTransform(m, transformations)).toArray(CCModel[]::new);
    }

    public static CCModel copyAndTransform(CCModel model, Transformation... transformations) {
        CCModel newModel = model.copy();
        Arrays.stream(transformations).forEach(newModel::apply);
        return newModel;
    }

}
