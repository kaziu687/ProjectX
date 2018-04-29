package de.kitsunealex.projectx.item;

import de.kitsunealex.projectx.util.EnumXycroniumColor;
import de.kitsunealex.projectx.util.ISubtypeHolder;

import java.util.Arrays;

public class ItemXycroniumIngot extends ItemBase implements ISubtypeHolder {

    public ItemXycroniumIngot() {
        super("xycronium_ingot");
    }

    @Override
    public String[] getSubNames() {
        return Arrays.stream(EnumXycroniumColor.values()).map(EnumXycroniumColor::getName).toArray(String[]::new);
    }

}
