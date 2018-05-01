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

package de.kitsunealex.projectx.compat.waila;

import de.kitsunealex.projectx.block.functional.BlockXycroniumLamp;
import de.kitsunealex.projectx.block.functional.BlockXycroniumLampInverted;
import de.kitsunealex.projectx.compat.IModule;
import de.kitsunealex.projectx.init.ModConfig;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModuleWAILA implements IModule {

    @Override
    public boolean isEnabled() {
        return ModConfig.COMPAT_WAILA;
    }

    @Override
    public String getDependencyModID() {
        return "waila";
    }

    @Override
    public String getName() {
        return "WAILA/Hwyla";
    }

    @Override
    public void handlePreInit(FMLPreInitializationEvent event) {
        FMLInterModComms.sendMessage("waila", "register", "de.kitsunealex.projectx.compat.waila.ModuleWAILA.callbackRegister");
    }

    @Override
    public void handleInit(FMLInitializationEvent event) {

    }

    @Override
    public void handlePreInitClient(FMLPreInitializationEvent event) {

    }

    @Override
    public void handleInitClient(FMLInitializationEvent event) {

    }

    public static void callbackRegister(IWailaRegistrar registrar) {
        DataProvider provider = new DataProvider();
        registrar.registerBodyProvider(provider, BlockXycroniumLamp.class);
        registrar.registerBodyProvider(provider, BlockXycroniumLampInverted.class);
    }

}
