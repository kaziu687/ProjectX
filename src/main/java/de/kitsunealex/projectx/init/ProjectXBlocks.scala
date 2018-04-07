package de.kitsunealex.projectx.init

import de.kitsunealex.projectx.block.BlockXycroniumOre
import net.minecraft.block.Block

object ProjectXBlocks {

  var XYCRONIUM_ORE: Block = _

  def registerBlocks: Unit = {
    XYCRONIUM_ORE = new BlockXycroniumOre
  }

  def registerTileEntities: Unit = {

  }

}
