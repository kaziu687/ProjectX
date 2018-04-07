package de.kitsunealex.projectx.client

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item

class ProjectXTab extends CreativeTabs("projectx.name") {

  override def getTabIconItem: Item = Items.apple

}

object ProjectXTab {

  val CREATIVE_TAB: CreativeTabs = new ProjectXTab

}
