package de.kitsunealex.projectx.item;

import de.kitsunealex.projectx.util.EnumXycroniumColor;
import de.kitsunealex.projectx.util.ISubtypeHolder;

import java.util.Arrays;

public class ItemXycroniumDust extends ItemBase implements ISubtypeHolder {

    public ItemXycroniumDust() {
        super("xycronium_dust");
    }

    @Override
    public String[] getSubNames() {
        return Arrays.stream(EnumXycroniumColor.values()).map(EnumXycroniumColor::getName).toArray(String[]::new);
    }

}
