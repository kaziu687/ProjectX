package de.kitsunealex.projectx.block;

import codechicken.lib.render.item.IItemRenderer;
import de.kitsunealex.projectx.api.client.IAnimationHandler;
import de.kitsunealex.projectx.api.client.RenderTypes;
import de.kitsunealex.projectx.client.render.RenderDefaultBlockGlow;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockAnimationHandler<T extends TileEntity> extends BlockBase<T> implements IAnimationHandler {

    public BlockAnimationHandler(String blockName, Material material) {
        super(blockName, material);
    }

    public BlockAnimationHandler(String blockName, Material material, Class<? extends ItemBlock> itemBlock) {
        super(blockName, material, itemBlock);
    }

    public BlockAnimationHandler(String blockName, Material material, MapColor mapColor) {
        super(blockName, material, mapColor);
    }

    public BlockAnimationHandler(String blockName, Material material, MapColor mapColor, Class<? extends ItemBlock> itemBlock) {
        super(blockName, material, mapColor, itemBlock);
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return RenderTypes.DEFAULT_BLOCK_GLOW;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer() {
        return RenderDefaultBlockGlow.INSTANCE;
    }

}
