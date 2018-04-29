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

package de.kitsunealex.projectx.api.power;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;

public enum EnumPowerClass {

    LOW         ("low", new ColourRGBA(0, 60, 201, 255), 64, 2000),
    MEDIUM      ("medium", new ColourRGBA(160, 0, 175, 255), 256, 8000),
    OMNIPOTENT  ("omnipotent", new ColourRGBA(203, 0, 0, 255), 2048, 32000);

    private String name;
    private Colour color;
    private int transfer;
    private int capacity;

    EnumPowerClass(String name, Colour color, int transfer, int capacity) {
        this.name = name;
        this.color = color;
        this.transfer = transfer;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public Colour getColor() {
        return color;
    }

    public int getColorRGBA() {
        return color.rgba();
    }

    public int getColorARGB() {
        return color.argb();
    }

    public int getTransfer() {
        return transfer;
    }

    public int getCapacity() {
        return capacity;
    }

}
