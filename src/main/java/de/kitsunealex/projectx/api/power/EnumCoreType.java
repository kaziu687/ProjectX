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

import java.util.function.Predicate;

public enum EnumCoreType {

    DIRECT("direct", new ColourRGBA(220, 220, 220, 255), connection -> true),
    RADIAL("radial", new ColourRGBA(100, 100, 220, 255), connection -> true);

    private String name;
    private Colour color;
    private Predicate<IConnection> predicate;

    EnumCoreType(String name, Colour color, Predicate<IConnection> predicate) {
        this.name = name;
        this.color = color;
        this.predicate = predicate;
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

    public Predicate<IConnection> getPredicate() {
        return predicate;
    }

}
