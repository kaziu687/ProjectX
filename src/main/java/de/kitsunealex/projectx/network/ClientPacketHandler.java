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

package de.kitsunealex.projectx.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class ClientPacketHandler implements ICustomPacketHandler.IClientPacketHandler {

    @Override
    public void handlePacket(PacketCustom packet, Minecraft minecraft, INetHandlerPlayClient handler) {
        switch(packet.getType()) {
            case 1:
                handleTilePacket(packet, minecraft.world);
                break;
        }
    }

    private void handleTilePacket(PacketCustom packet, WorldClient world) {
        final BlockPos pos = packet.readPos();
        final NBTTagCompound compound = packet.readNBTTagCompound();
        final boolean rerender = packet.readBoolean();
        TileEntity tile = world.getTileEntity(pos);

        if(tile != null) {
            IBlockState state = world.getBlockState(pos);
            tile.readFromNBT(compound);
            tile.markDirty();
            world.notifyBlockUpdate(pos, state, state, 3);

            if(rerender) {
                world.markBlockRangeForRenderUpdate(pos, pos);
            }
        }
    }

}
