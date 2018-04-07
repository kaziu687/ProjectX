package de.kitsunealex.projectx.api;

import cpw.mods.fml.common.Loader;

public class ProjectXAPI {

    public static boolean isModLoaded() {
        return Loader.isModLoaded("projectx");
    }

}
