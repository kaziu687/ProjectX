package de.kitsunealex.projectx.block

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess

trait TAnimationHandler {

  @SideOnly(Side.CLIENT)
  def getAnimationIcon(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon = getAnimationIcon(world.getBlockMetadata(x, y, z), side)

  @SideOnly(Side.CLIENT)
  def getAnimationIcon(meta: Int, side: Int): IIcon

  @SideOnly(Side.CLIENT)
  def getAnimationColor(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Int = getAnimationColor(world.getBlockMetadata(x, y, z), side)

  @SideOnly(Side.CLIENT)
  def getAnimationColor(meta: Int, side: Int): Int

  @SideOnly(Side.CLIENT)
  def getAnimationBrightness(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Int = getAnimationBrightness(world.getBlockMetadata(x, y, z), side)

  @SideOnly(Side.CLIENT)
  def getAnimationBrightness(meta: Int, side: Int): Int

}
