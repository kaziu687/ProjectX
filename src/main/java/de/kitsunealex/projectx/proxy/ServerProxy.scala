package de.kitsunealex.projectx.proxy
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}

class ServerProxy extends CommonProxy {

  override def handlePreInit(event: FMLPreInitializationEvent): Unit = {
    super.handlePreInit(event)
  }

  override def handleInit(event: FMLInitializationEvent): Unit = {
    super.handleInit(event)
  }

  override def handlePostInit(event: FMLPostInitializationEvent): Unit = {
    super.handlePostInit(event)
  }

}
