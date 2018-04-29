package de.kitsunealex.projectx.item;

import de.kitsunealex.projectx.util.EnumXycroniumColor;
import de.kitsunealex.projectx.util.ISubtypeHolder;

import java.util.Arrays;

public class ItemXycroniumNugget extends ItemBase implements ISubtypeHolder {

    public ItemXycroniumNugget() {
        super("xycronium_nugget");
    }

    @Override
    public String[] getSubNames() {
        return Arrays.stream(EnumXycroniumColor.values()).map(EnumXycroniumColor::getName).toArray(String[]::new);
    }

}
