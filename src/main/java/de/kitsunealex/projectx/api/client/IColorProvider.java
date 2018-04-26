package de.kitsunealex.projectx.api.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IColorProvider {

    @SideOnly(Side.CLIENT)
    int getColorMultiplier(int meta, int side);

    @SideOnly(Side.CLIENT)
    default int getColorMultiplier(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return getColorMultiplier(state.getBlock().getMetaFromState(state), side);
    }

}
