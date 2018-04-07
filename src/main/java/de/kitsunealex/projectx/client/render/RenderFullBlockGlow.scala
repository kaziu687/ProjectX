package de.kitsunealex.projectx.client.render

import codechicken.lib.render.uv.IconTransformation
import codechicken.lib.render.{CCModel, CCRenderState}
import codechicken.lib.vec.{Cuboid6, Translation, Vector3}
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
import cpw.mods.fml.relauncher.{Side, SideOnly}
import de.kitsunealex.projectx.api.client.RenderTypes
import de.kitsunealex.projectx.block.TAnimationHandler
import net.minecraft.block.Block
import net.minecraft.client.renderer.{RenderBlocks, RenderHelper}
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess
import org.lwjgl.opengl.GL11

@SideOnly(Side.CLIENT)
class RenderFullBlockGlow extends ISimpleBlockRenderingHandler {

  override def renderWorldBlock(world: IBlockAccess, x: Int, y: Int, z: Int, block: Block, modelId: Int, renderer: RenderBlocks): Boolean = {
    val handler: TAnimationHandler = block.asInstanceOf[TAnimationHandler]
    val meta: Int = world.getBlockMetadata(x, y, z)
    CCRenderState.reset()
    CCRenderState.bindModel(RenderFullBlockGlow.MODEL.copy().apply(new Translation(new Vector3(x, y, z))))

    for(side <- 0 until 6) {
      val texture: IIcon = handler.getAnimationIcon(meta, side)
      CCRenderState.setVertexRange(side * 4, side * 4 + 4)
      CCRenderState.setBrightness(handler.getAnimationBrightness(meta, side))
      CCRenderState.setColour(handler.getAnimationColor(meta, side))
      CCRenderState.render(new IconTransformation(texture))
    }

    return true
  }

  override def renderInventoryBlock(block: Block, meta: Int, modelId: Int, renderer: RenderBlocks): Unit = {
    val handler: TAnimationHandler = block.asInstanceOf[TAnimationHandler]
    GL11.glPushMatrix()
    RenderHelper.disableStandardItemLighting()
    GL11.glTranslated(-0.5D, -0.5D, -0.5)
    CCRenderState.reset()
    CCRenderState.bindModel(RenderFullBlockGlow.MODEL)
    CCRenderState.startDrawing(GL11.GL_QUADS)

    for(side <- 0 until 6) {
      val texture: IIcon = handler.getAnimationIcon(meta, side)
      CCRenderState.setVertexRange(side * 4, side * 4 + 4)
      CCRenderState.setBrightness(handler.getAnimationBrightness(meta, side))
      CCRenderState.setColour(handler.getAnimationColor(meta, side))
      CCRenderState.render(new IconTransformation(texture))
    }

    CCRenderState.draw()
    RenderHelper.enableStandardItemLighting()
    GL11.glPopMatrix()
  }

  override def shouldRender3DInInventory(modelId: Int): Boolean = true

  override def getRenderId: Int = RenderTypes.FULL_BLOCK_GLOW

}

@SideOnly(Side.CLIENT)
object RenderFullBlockGlow {

  private val MODEL: CCModel = CCModel.quadModel(24).generateBlock(0, new Cuboid6(0D, 0D, 0D, 1D, 1D, 1D)).computeNormals()

}
