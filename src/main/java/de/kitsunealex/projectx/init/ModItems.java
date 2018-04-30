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
