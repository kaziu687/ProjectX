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

package de.kitsunealex.projectx.compat;

import de.kitsunealex.projectx.compat.albedo.ModuleAlbedo;
import de.kitsunealex.projectx.compat.thaumcraft.ModuleThaumcraft;
import de.kitsunealex.projectx.compat.waila.ModuleWAILA;
import de.kitsunealex.projectx.init.ModConfig;

public class ModModules {

    public static void registerModules() {
        if (ModConfig.COMPAT_ALBEDO) {
            ModuleHandler.INSTANCE.registerModule(new ModuleAlbedo());
        }

        if (ModConfig.COMPAT_THAUMCRAFT) {
            ModuleHandler.INSTANCE.registerModule(new ModuleThaumcraft());
        }

        if (ModConfig.COMPAT_WAILA) {
            ModuleHandler.INSTANCE.registerModule(new ModuleWAILA());
        }
    }
}
