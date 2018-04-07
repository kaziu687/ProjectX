package de.kitsunealex.projectx

import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.{Mod, SidedProxy}
import de.kitsunealex.projectx.proxy.CommonProxy

@Mod(modid = "projectx", useMetadata = true, modLanguage = "scala")
object ProjectX {

  @SidedProxy(clientSide = "de.kitsunealex.projectx.proxy.ClientProxy", serverSide = "de.kitsunealex.projectx.proxy.ServerProxy")
  var PROXY: CommonProxy = _

  @EventHandler
  def handlePreInit(event: FMLPreInitializationEvent): Unit = {
    PROXY.handlePreInit(event)
  }

  @EventHandler
  def handleInit(event: FMLInitializationEvent): Unit = {
    PROXY.handleInit(event)
  }

  @EventHandler
  def handlePostInit(event: FMLPostInitializationEvent): Unit = {
    PROXY.handlePostInit(event)
  }

}
