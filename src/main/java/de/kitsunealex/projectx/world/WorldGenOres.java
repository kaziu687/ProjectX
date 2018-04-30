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

package de.kitsunealex.projectx.world;

import com.google.common.base.Predicate;
import de.kitsunealex.projectx.init.ModBlocks;
import de.kitsunealex.projectx.init.ModConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenOres implements IWorldGenerator {

    private static final Predicate<IBlockState> STONE_MATCHER = state -> state.getBlock() == Blocks.STONE;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.getDimension()) {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 0:
                generateOverworld(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    @SuppressWarnings("deprecation")
    private void generateOverworld(World world, Random random, int blockX, int blockZ) {
        generateOre(ModBlocks.XYCRONIUM_ORE.getStateFromMeta(0), STONE_MATCHER, world, random, blockX, blockZ, ModConfig.ORE_BLUE_PARAMS);
        generateOre(ModBlocks.XYCRONIUM_ORE.getStateFromMeta(1), STONE_MATCHER, world, random, blockX, blockZ, ModConfig.ORE_GREEN_PARAMS);
        generateOre(ModBlocks.XYCRONIUM_ORE.getStateFromMeta(2), STONE_MATCHER, world, random, blockX, blockZ, ModConfig.ORE_RED_PARAMS);
        generateOre(ModBlocks.XYCRONIUM_ORE.getStateFromMeta(3), STONE_MATCHER, world, random, blockX, blockZ, ModConfig.ORE_DARK_PARAMS);
        generateOre(ModBlocks.XYCRONIUM_ORE.getStateFromMeta(4), STONE_MATCHER, world, random, blockX, blockZ, ModConfig.ORE_LIGHT_PARAMS);
    }

    private void generateNether(World world, Random random, int blockX, int blockZ) {

    }

    private void generateEnd(World world, Random random, int blockX, int blockZ) {

    }

    private void generateOre(IBlockState state, Predicate<IBlockState> matcher, World world, Random rand, int blockX, int blockZ, int... params) {
        for(int i = 0; i < params[0]; i++) {
            int x = blockX + rand.nextInt(16);
            int y = params[1] + rand.nextInt(params[2] - params[1]);
            int z = blockZ + rand.nextInt(16);
            int v = params[3] + rand.nextInt(params[4] - params[3]);
            new WorldGenMinable(state, v, matcher).generate(world, rand, new BlockPos(x, y, z));
        }
    }

}
