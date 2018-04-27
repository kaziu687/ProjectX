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
