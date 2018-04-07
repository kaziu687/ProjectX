package de.kitsunealex.projectx.item

import java.util

import cpw.mods.fml.relauncher.{Side, SideOnly}
import de.kitsunealex.projectx.block.BlockBase
import net.minecraft.block.Block
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemBlock, ItemStack}

class ItemBlockBase(block: Block) extends ItemBlock(block) {

  setHasSubtypes(block.asInstanceOf[BlockBase].getSubNames != null)

  override def getMetadata(damage : Int): Int = if(block.asInstanceOf[BlockBase].getSubNames != null) damage else super.getMetadata(damage)

  @SideOnly(Side.CLIENT)
  override def getSubItems(item: Item, tab: CreativeTabs, items: util.List[_]): Unit = block.getSubBlocks(item, tab, items)

  @SideOnly(Side.CLIENT)
  override def getUnlocalizedName(stack: ItemStack): String = {
    if(block.asInstanceOf[BlockBase].getSubNames != null) {
      return s"${getUnlocalizedName()}.${block.asInstanceOf[BlockBase].getSubNames(stack.getItemDamage)}"
    }
    else {
      return getUnlocalizedName()
    }
  }

}
