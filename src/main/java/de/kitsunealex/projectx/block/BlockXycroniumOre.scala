package de.kitsunealex.projectx.block

import cpw.mods.fml.relauncher.{Side, SideOnly}
import de.kitsunealex.projectx.api.client.RenderTypes
import de.kitsunealex.projectx.api.color.EnumXycroniumColor
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon

class BlockXycroniumOre extends BlockBase("xycronium_ore", Material.rock, EnumXycroniumColor.toStringArray) with TAnimationHandler {

  private var texture: Array[IIcon] = _

  setHardness(1.7F)
  setResistance(1.5F)

  @SideOnly(Side.CLIENT)
  override def registerBlockIcons(register : IIconRegister): Unit = {
    texture = new Array[IIcon](6)
    for(i <- 0 until 5) texture(i) = register.registerIcon(s"projectx:$getBlockName/${getBlockName}_${getSubNames(i)}")
    texture(5) = register.registerIcon(s"projectx:$getBlockName/${getBlockName}_effect")
  }

  @SideOnly(Side.CLIENT)
  override def getIcon(meta: Int, side: Int): IIcon = texture(meta)

  @SideOnly(Side.CLIENT)
  override def getAnimationIcon(meta: Int, side: Int): IIcon = texture(5)

  @SideOnly(Side.CLIENT)
  override def getAnimationColor(meta: Int, side: Int): Int = EnumXycroniumColor.VALUES(meta).getColor.rgba()

  @SideOnly(Side.CLIENT)
  override def getAnimationBrightness(meta: Int, side: Int): Int = 0x00F000F0

  @SideOnly(Side.CLIENT)
  override def getRenderType: Int = RenderTypes.FULL_BLOCK_GLOW

}
