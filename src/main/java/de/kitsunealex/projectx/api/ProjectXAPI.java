package de.kitsunealex.projectx.api;

import net.minecraftforge.fml.common.Loader;

public class ProjectXAPI {

    public static boolean isModLoaded() {
        return Loader.isModLoaded("projectx");
    }

}
