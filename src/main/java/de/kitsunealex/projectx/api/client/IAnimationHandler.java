package de.kitsunealex.projectx.api.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IAnimationHandler {

    @SideOnly(Side.CLIENT)
    TextureAtlasSprite getAnimationTexture(int meta, int side);

    @SideOnly(Side.CLIENT)
    default TextureAtlasSprite getAnimationTexture(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return getAnimationTexture(state.getBlock().getMetaFromState(state), side);
    }

    @SideOnly(Side.CLIENT)
    int getAnimationColor(int meta, int side);

    @SideOnly(Side.CLIENT)
    default int getAnimationColor(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return getAnimationColor(state.getBlock().getMetaFromState(state), side);
    }

    @SideOnly(Side.CLIENT)
    int getAnimationBrightness(int meta, int side);

    @SideOnly(Side.CLIENT)
    default int getAnimationBrightness(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return getAnimationBrightness(state.getBlock().getMetaFromState(state), side);
    }

}
