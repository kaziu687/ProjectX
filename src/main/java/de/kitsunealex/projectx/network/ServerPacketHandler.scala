package de.kitsunealex.projectx.network

import codechicken.lib.packet.PacketCustom
import codechicken.lib.packet.PacketCustom.IServerPacketHandler
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.network.play.INetHandlerPlayServer

class ServerPacketHandler extends IServerPacketHandler {

  override def handlePacket(packet: PacketCustom, player: EntityPlayerMP, handler: INetHandlerPlayServer): Unit = {

  }

}
