package de.kitsunealex.projectx.block

import java.util

import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.{Side, SideOnly}
import de.kitsunealex.projectx.client.ProjectXTab
import de.kitsunealex.projectx.item.ItemBlockBase
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemBlock, ItemStack}
import net.minecraft.util.MovingObjectPosition
import net.minecraft.world.World

class BlockBase(blockName: String, material: Material, subNames: Array[String]) extends Block(material) {

  setBlockName(s"projectx.$blockName")
  setCreativeTab(ProjectXTab.CREATIVE_TAB)
  GameRegistry.registerBlock(this, getItemBlock, s"projectx.$blockName")

  def this(blockName: String, material: Material) = this(blockName, material, null)

  override def damageDropped(meta: Int): Int = if(subNames != null) meta else super.damageDropped(meta)

  override def getPickBlock(target: MovingObjectPosition, world: World, x: Int, y: Int, z: Int, player: EntityPlayer): ItemStack = {
    if(subNames != null) {
      return new ItemStack(this, 1, world.getBlockMetadata(x, y, z))
    }
    else {
      return new ItemStack(this, 1, 0)
    }
  }

  @SideOnly(Side.CLIENT)
  override def getSubBlocks(item: Item, tab: CreativeTabs, items: util.List[_]): Unit = {
    if(subNames != null) {
      for(i <- 0 until subNames.length) items.asInstanceOf[util.List[ItemStack]].add(new ItemStack(this, 1, i))
    }
    else {
      items.asInstanceOf[util.List[ItemStack]].add(new ItemStack(this, 1, 0))
    }
  }

  def getItemBlock: Class[_ <: ItemBlock] = classOf[ItemBlockBase]

  def getBlockName: String = blockName

  def getSubNames: Array[String] = subNames

}
