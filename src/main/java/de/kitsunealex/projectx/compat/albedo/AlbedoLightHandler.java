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
import codechicken.lib.colour.ColourRGBA;
import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import codechicken.lib.vec.Vector3;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.INetHandlerPlayClient;

import java.util.List;

public class AlbedoLightHandler implements ICustomPacketHandler.IClientPacketHandler {

    public static final AlbedoLightHandler INSTANCE = new AlbedoLightHandler();
    private static List<AlbedoLight> LIGHTS = Lists.newArrayList();

    @Override
    public void handlePacket(PacketCustom packet, Minecraft minecraft, INetHandlerPlayClient handler) {
        switch(packet.getType()) {
            case 1:
                handleAddLightPacket(packet);
                break;
            case 2:
                handleRemoveLightPacket(packet);
                break;
            case 3:
                handleChangeLightPacket(packet);
                break;
        }
    }

    private void handleAddLightPacket(PacketCustom packet) {
        final Vector3 position = packet.readVector();
        final Colour color = new ColourRGBA(packet.readInt());
        final float radius = packet.readFloat();
        LIGHTS.add(new AlbedoLight(position, color, radius));
        System.out.println("HELLO WORLD");
    }

    private void handleRemoveLightPacket(PacketCustom packet) {
        final Vector3 position = packet.readVector();
        LIGHTS.stream()
                .filter(light -> light.getPosition().equals(position))
                .findFirst()
                .ifPresent(light -> LIGHTS.remove(light));
    }

    private void handleChangeLightPacket(PacketCustom packet) {
        final Vector3 position = packet.readVector();
        final Colour color = new ColourRGBA(packet.readInt());
        final float radius = packet.readFloat();
        LIGHTS.stream()
                .filter(light -> light.getPosition().equals(position))
                .findFirst()
                .ifPresent(light -> {
                    light.setColor(color);
                    light.setRadius(radius);
                });
    }

    public void addLight(Vector3 position, Colour color, float radius) {
        PacketCustom packet = new PacketCustom(ModuleAlbedo.NETWORK_CHANNEL, 1);
        packet.writeVector(position);
        packet.writeInt(color.rgba());
        packet.writeFloat(radius);
        packet.compress().sendToClients();
    }

    public void removeLight(Vector3 position) {
        PacketCustom packet = new PacketCustom(ModuleAlbedo.NETWORK_CHANNEL, 2);
        packet.writeVector(position);
        packet.compress().sendToClients();
    }

    public void changeLight(Vector3 position, Colour color, float radius) {
        PacketCustom packet = new PacketCustom(ModuleAlbedo.NETWORK_CHANNEL, 3);
        packet.writeVector(position);
        packet.writeInt(color.rgba());
        packet.writeFloat(radius);
        packet.compress().sendToClients();
    }

    public ImmutableList<AlbedoLight> getLights() {
        return ImmutableList.copyOf(LIGHTS);
    }

}
