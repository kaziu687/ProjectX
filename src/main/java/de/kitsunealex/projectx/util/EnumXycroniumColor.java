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

package de.kitsunealex.projectx.util;

import codechicken.lib.colour.Colour;
import codechicken.lib.colour.ColourRGBA;

public enum EnumXycroniumColor {

    BLUE    ("blue", new ColourRGBA(0, 100, 255, 255)),
    GREEN   ("green", new ColourRGBA(16711935)),
    RED     ("red", new ColourRGBA(-16776961)),
    DARK    ("dark", new ColourRGBA(30, 30, 30, 255)),
    LIGHT   ("light", new ColourRGBA(-1));

    private String name;
    private Colour color;

    EnumXycroniumColor(String name, Colour color){
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
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

}
