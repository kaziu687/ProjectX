package de.kitsunealex.projectx;

import de.kitsunealex.projectx.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import static de.kitsunealex.projectx.util.Constants.*;

@Mod(modid = MODID, name = NAME, version = VERSION, useMetadata = true)
public class ProjectX {

    @Mod.Instance(MODID)
    public static ProjectX INSTANCE;
    @SidedProxy(clientSide = CSIDE, serverSide = SSIDE)
    public static CommonProxy PROXY;
    public static Logger LOGGER;

    @Mod.EventHandler
    public void handlePreInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        PROXY.handlePreInit(event);
    }

    @Mod.EventHandler
    public void handleInit(FMLInitializationEvent event) {
        PROXY.handleInit(event);
    }

    @Mod.EventHandler
    public void handlePostInit(FMLPostInitializationEvent event) {
        PROXY.handlePostInit(event);
    }

}
