package de.kitsunealex.projectx.network

import codechicken.lib.packet.PacketCustom
import codechicken.lib.packet.PacketCustom.IClientPacketHandler
import net.minecraft.client.Minecraft
import net.minecraft.network.play.INetHandlerPlayClient

class ClientPacketHandler extends IClientPacketHandler {

  override def handlePacket(packet: PacketCustom, minecraft: Minecraft, handler: INetHandlerPlayClient): Unit = {

  }

}
