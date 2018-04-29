/*
 * This file is part of ProjectX.
 * Copyright (c) 2015 - 2018, KitsuneAlex, All rights reserved.
 *
 * ProjectX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ProjectX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ProjectX.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package de.kitsunealex.projectx.init;

import de.kitsunealex.projectx.block.BlockXycroniumOre;
import de.kitsunealex.projectx.block.BlockXycroniumStorage;
import de.kitsunealex.projectx.block.decorative.*;
import de.kitsunealex.projectx.block.functional.BlockXycroniumLamp;
import de.kitsunealex.projectx.block.functional.BlockXycroniumLampInverted;
import de.kitsunealex.projectx.tile.TileEntityXycroniumLamp;
import de.kitsunealex.projectx.util.Constants;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static Block XYCRONIUM_ORE;
    public static Block XYCRONIUM_BLOCK;
    public static Block XYCRONIUM_BRICKS;
    public static Block INFUSED_BRICKS;
    public static Block GLASS_VIEWER;
    public static Block XYCRONIUM_PLATE;
    public static Block XYCRONIUM_PLATFORM;
    public static Block XYCRONIUM_STRUCTURE;
    public static Block XYCRONIUM_SHIELD;
    public static Block XYCRONIUM_LAMP;
    public static Block XYCRONIUM_LAMP_INVERTED;

    public static void registerBlocks() {
        XYCRONIUM_ORE = new BlockXycroniumOre();
        XYCRONIUM_BLOCK = new BlockXycroniumStorage();
        XYCRONIUM_BRICKS = new BlockXycroniumBricks();
        INFUSED_BRICKS = new BlockInfusedBricks();
        GLASS_VIEWER = new BlockGlassViewer();
        XYCRONIUM_PLATE = new BlockXycroniumPlate();
        XYCRONIUM_PLATFORM = new BlockXycroniumPlatform();
        XYCRONIUM_STRUCTURE = new BlockXycroniumStructure();
        XYCRONIUM_SHIELD = new BlockXycroniumShield();
        XYCRONIUM_LAMP = new BlockXycroniumLamp();
        XYCRONIUM_LAMP_INVERTED = new BlockXycroniumLampInverted();
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityXycroniumLamp.class, String.format("tileentity.%s.xycronium_lamp", Constants.MODID));
    }

}
