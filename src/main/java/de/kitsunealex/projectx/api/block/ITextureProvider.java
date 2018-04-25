package de.kitsunealex.projectx.api.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ITextureProvider {

    @SideOnly(Side.CLIENT)
    TextureAtlasSprite getTexture(int meta, int side);

    @SideOnly(Side.CLIENT)
    default TextureAtlasSprite getTexture(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return getTexture(state.getBlock().getMetaFromState(state), side);
    }

}
