package de.kitsunealex.projectx.proxy
import codechicken.lib.packet.PacketCustom
import cpw.mods.fml.client.registry.RenderingRegistry
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import de.kitsunealex.projectx.api.client.RenderTypes
import de.kitsunealex.projectx.client.render.RenderFullBlockGlow
import de.kitsunealex.projectx.network.ClientPacketHandler

class ClientProxy extends CommonProxy {

  override def handlePreInit(event: FMLPreInitializationEvent): Unit = {
    super.handlePreInit(event)
    registerRenderers
  }

  override def handleInit(event: FMLInitializationEvent): Unit = {
    super.handleInit(event)
    PacketCustom.assignHandler("projectx", new ClientPacketHandler)
  }

  override def handlePostInit(event: FMLPostInitializationEvent): Unit = {
    super.handlePostInit(event)
  }

  private def registerRenderers: Unit = {
    RenderTypes.FULL_BLOCK_GLOW = RenderingRegistry.getNextAvailableRenderId
    RenderingRegistry.registerBlockHandler(RenderTypes.FULL_BLOCK_GLOW, new RenderFullBlockGlow)
  }

}
