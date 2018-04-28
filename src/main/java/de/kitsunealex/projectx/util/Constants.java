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

public class Constants {

    public static final String MODID = "projectx";
    public static final String NAME = "ProjectX 2";
    public static final String VERSION = "2.2.0";
    public static final String DEPS = "required-after:codechickenlib" +
            "required-after:redstoneflux" +
            "after:hwyla" +
            "after:jei";
    private static final String PROXY_PACKAGE = "de.kitsunealex.projectx.proxy";
    public static final String CSIDE = PROXY_PACKAGE + ".ClientProxy";
    public static final String SSIDE = PROXY_PACKAGE + ".ServerProxy";

}
