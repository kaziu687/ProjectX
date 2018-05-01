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

package de.kitsunealex.projectx.compat;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import de.kitsunealex.projectx.ProjectX;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.stream.Collectors;

public class ModuleHandler {

    public static final ModuleHandler INSTANCE = new ModuleHandler();
    private static List<IModule> MODULES = Lists.newArrayList();
    private static List<String> ENABLED_MODULES;

    public void registerModule(IModule module) {
        MODULES.add(module);
    }

    public void handlePreInit(FMLPreInitializationEvent event) {
        ENABLED_MODULES = MODULES.stream()
                .filter(IModule::isEnabled)
                .filter(module -> Loader.isModLoaded(module.getDependencyModID()))
                .peek(module -> ProjectX.LOGGER.info("Found mod {}, enabling compat module {}...", module.getDependencyModID(), module.getName()))
                .map(IModule::getName)
                .collect(Collectors.toList());
        MODULES.stream()
                .filter(module -> ENABLED_MODULES.contains(module.getName()))
                .forEach(module -> module.handlePreInit(event));
    }

    public void handleInit(FMLInitializationEvent event) {
        MODULES.stream()
                .filter(module -> ENABLED_MODULES.contains(module.getName()))
                .forEach(module -> module.handleInit(event));
    }

    @SideOnly(Side.CLIENT)
    public void handlePreInitClient(FMLPreInitializationEvent event) {
        MODULES.stream()
                .filter(module -> ENABLED_MODULES.contains(module.getName()))
                .forEach(module -> module.handlePreInitClient(event));
    }

    @SideOnly(Side.CLIENT)
    public void handleInitClient(FMLInitializationEvent event) {
        MODULES.stream()
                .filter(module -> ENABLED_MODULES.contains(module.getName()))
                .forEach(module -> module.handleInitClient(event));
    }

    public boolean isModuleEnabled(String name) {
        return ENABLED_MODULES.contains(name);
    }

    public ImmutableList<IModule> getModules() {
        return ImmutableList.copyOf(MODULES);
    }

}
