package de.kitsunealex.projectx.proxy

import codechicken.lib.packet.PacketCustom
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import de.kitsunealex.projectx.init.{ProjectXBlocks, ProjectXItems}
import de.kitsunealex.projectx.network.ServerPacketHandler

class CommonProxy {

  def handlePreInit(event: FMLPreInitializationEvent): Unit = {
    ProjectXBlocks.registerBlocks
    ProjectXItems.registerItems
  }

  def handleInit(event: FMLInitializationEvent): Unit = {
    ProjectXBlocks.registerTileEntities
    PacketCustom.assignHandler("projectx", new ServerPacketHandler)
  }

  def handlePostInit(event: FMLPostInitializationEvent): Unit = {

  }

}
