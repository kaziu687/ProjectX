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

import codechicken.lib.packet.PacketCustom;
import de.kitsunealex.projectx.compat.IModule;
import de.kitsunealex.projectx.init.ModConfig;
import de.kitsunealex.projectx.util.Constants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModuleAlbedo implements IModule {

    public static final String NETWORK_CHANNEL = String.format("%s_albedo", Constants.MODID);

    @Override
    public boolean isEnabled() {
        return ModConfig.COMPAT_ALBEDO;
    }

    @Override
    public String getDependencyModID() {
        return "albedo";
    }

    @Override
    public String getName() {
        return "Albedo";
    }

    @Override
    public void handlePreInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void handleInit(FMLInitializationEvent event) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handlePreInitClient(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(AlbedoLightHandler.INSTANCE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleInitClient(FMLInitializationEvent event) {
        PacketCustom.assignHandler(ModuleAlbedo.NETWORK_CHANNEL, AlbedoLightHandler.INSTANCE);
    }

}
