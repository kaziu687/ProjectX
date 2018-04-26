package de.kitsunealex.projectx.init;

import de.kitsunealex.projectx.block.BlockInfusedBricks;
import de.kitsunealex.projectx.block.BlockXycroniumBricks;
import de.kitsunealex.projectx.block.BlockXycroniumOre;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block XYCRONIUM_ORE;
    public static Block XYCRONIUM_BLOCK;
    public static Block XYCRONIUM_BRICKS;
    public static Block INFUSED_BRICKS;

    public static void registerBlocks() {
        XYCRONIUM_ORE = new BlockXycroniumOre();
        XYCRONIUM_BRICKS = new BlockXycroniumBricks();
        INFUSED_BRICKS = new BlockInfusedBricks();
    }

    public static void registerTileEntities() {

    }

}
