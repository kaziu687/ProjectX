package de.kitsunealex.projectx.init;

import de.kitsunealex.projectx.block.BlockXycroniumOre;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block XYCRONIUM_ORE;

    public static void registerBlocks() {
        XYCRONIUM_ORE = new BlockXycroniumOre();
    }

    public static void registerTileEntities() {

    }

}
