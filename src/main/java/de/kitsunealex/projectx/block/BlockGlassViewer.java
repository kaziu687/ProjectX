package de.kitsunealex.projectx.block;

import de.kitsunealex.projectx.api.client.ICTMBlock;
import de.kitsunealex.projectx.util.Constants;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlassViewer extends BlockBase implements ICTMBlock {

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite[] texture;

    public BlockGlassViewer() {
        super("glass_viewer", Material.GLASS);
        setHardness(1.6F);
        setResistance(2.8F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {
        String path = String.format("blocks/%s/%s", blockName, blockName);
        texture = registerConnectedTexture(map, new ResourceLocation(Constants.MODID, path));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta, int side) {
        return texture[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getConnectedTexture(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return texture;
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }

}