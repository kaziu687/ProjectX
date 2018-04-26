package de.kitsunealex.projectx.init;

import de.kitsunealex.projectx.block.*;
import net.minecraft.block.Block;

public class ModBlocks {

    public static Block XYCRONIUM_ORE;
    public static Block XYCRONIUM_BLOCK;
    public static Block XYCRONIUM_BRICKS;
    public static Block INFUSED_BRICKS;
    public static Block GLASS_VIEWER;

    public static void registerBlocks() {
        XYCRONIUM_ORE = new BlockXycroniumOre();
        XYCRONIUM_BLOCK = new BlockXycroniumStorage();
        XYCRONIUM_BRICKS = new BlockXycroniumBricks();
        INFUSED_BRICKS = new BlockInfusedBricks();
        GLASS_VIEWER = new BlockGlassViewer();
    }

    public static void registerTileEntities() {

    }

}
