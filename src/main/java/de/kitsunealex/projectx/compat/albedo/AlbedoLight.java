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

package de.kitsunealex.projectx.compat.albedo;

import codechicken.lib.colour.Colour;
import codechicken.lib.vec.Vector3;

public class AlbedoLight {

    private final Vector3 position;
    private Colour color;
    private float radius;

    public AlbedoLight(Vector3 position, Colour color, float radius) {
        this.position = position;
        this.color = color;
        this.radius = radius;
    }

    public void setColor(Colour color) {
        this.color = color;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Colour getColor() {
        return color;
    }

    public float getRadius() {
        return radius;
    }

}
