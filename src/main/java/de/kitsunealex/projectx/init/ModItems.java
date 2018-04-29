package de.kitsunealex.projectx.init;

import de.kitsunealex.projectx.item.ItemXycroniumCrystal;
import de.kitsunealex.projectx.item.ItemXycroniumDust;
import de.kitsunealex.projectx.item.ItemXycroniumIngot;
import de.kitsunealex.projectx.item.ItemXycroniumNugget;
import net.minecraft.item.Item;

public class ModItems {

    public static Item XYCRONIUM_CRYSTAL;
    public static Item XYCRONIUM_INGOT;
    public static Item XYCRONIUM_DUST;
    public static Item XYCRONIUM_NUGGET;

    public static void registerItems() {
        XYCRONIUM_CRYSTAL = new ItemXycroniumCrystal();
        XYCRONIUM_INGOT = new ItemXycroniumIngot();
        XYCRONIUM_DUST = new ItemXycroniumDust();
        XYCRONIUM_NUGGET = new ItemXycroniumNugget();
    }

}
