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

import de.kitsunealex.projectx.ProjectX;
import net.minecraftforge.common.config.Configuration;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;

public class ModConfig {

    private static final String CATEGORY_GENERAL = "general";
    private static final String CATEGORY_CLIENT = "client";
    private static final String CATEGORY_WORLD = "world";
    private static final int[] DEFAULT_ORE_PARAMS = {3, 20, 52, 4, 8};
    public static boolean SUPPORTER_BADGES;
    public static int ANIMATION_RESOLUTION;
    public static int GENERATOR_WEIGHT;
    public static int[] ORE_BLUE_PARAMS;
    public static int[] ORE_GREEN_PARAMS;
    public static int[] ORE_RED_PARAMS;
    public static int[] ORE_DARK_PARAMS;
    public static int[] ORE_LIGHT_PARAMS;

    public static void loadConfig(File file) {
        Configuration config = new Configuration(file);
        StopWatch timer = new StopWatch();
        timer.start();
        config.load();
        addProperties(config);
        config.save();
        timer.stop();
        ProjectX.LOGGER.info("Loaded config file in %lms!", timer.getTime());
    }

    private static void addProperties(Configuration config) {
        SUPPORTER_BADGES = config.get(CATEGORY_GENERAL, "supporterBadges", true, "Enable/disable supporter badges").getBoolean();

        ANIMATION_RESOLUTION = config.get(CATEGORY_CLIENT, "animationResolution", 32, "Resoluion of the animation texture for blocks & items", 16, 128).getInt();

        GENERATOR_WEIGHT = config.get(CATEGORY_WORLD, "generatorWeight", 1, "The weight of all world generators", 0, Integer.MAX_VALUE).getInt();
        ORE_BLUE_PARAMS = config.get(CATEGORY_WORLD, "xycroniumOreBlue", DEFAULT_ORE_PARAMS, "Chance, min height, max height, min vein size, max vein size").getIntList();
        ORE_GREEN_PARAMS = config.get(CATEGORY_WORLD, "xycroniumOreGreen", DEFAULT_ORE_PARAMS, "Chance, min height, max height, min vein size, max vein size").getIntList();
        ORE_RED_PARAMS = config.get(CATEGORY_WORLD, "xycroniumOreRed", DEFAULT_ORE_PARAMS, "Chance, min height, max height, min vein size, max vein size").getIntList();
        ORE_DARK_PARAMS = config.get(CATEGORY_WORLD, "xycroniumOreDark", DEFAULT_ORE_PARAMS, "Chance, min height, max height, min vein size, max vein size").getIntList();
        ORE_LIGHT_PARAMS = config.get(CATEGORY_WORLD, "xycroniumOreLight", DEFAULT_ORE_PARAMS, "Chance, min height, max height, min vein size, max vein size").getIntList();
    }

}
