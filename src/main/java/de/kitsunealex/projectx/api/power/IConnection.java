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

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IConnection {

    BlockPos getSourcePos();

    BlockPos getTargetPos();

    EnumPowerClass getSourcePowerClass();

    EnumPowerClass getTargetPowerClass();

    void onConnected(BlockPos pos, World world);

    void onDisconntected(BlockPos pos, World world);

    void onPowerTransfered(BlockPos pos, World world);

}
