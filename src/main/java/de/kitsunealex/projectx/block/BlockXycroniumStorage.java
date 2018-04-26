package de.kitsunealex.projectx.block;

import de.kitsunealex.projectx.api.client.ICTMBlock;
import de.kitsunealex.projectx.api.client.Textures;
import de.kitsunealex.projectx.api.color.EnumXycroniumColor;
import de.kitsunealex.projectx.util.Constants;
import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockXycroniumStorage extends BlockAnimationHandler implements ISubtypeHolder, ICTMBlock {

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite[] texture;

    public BlockXycroniumStorage() {
        super("xycronium_block", Material.ROCK);
        setHardness(1.4F);
        setResistance(1.5F);
    }

    @Override
    public String[] getSubNames() {
        return EnumXycroniumColor.toStringArray();
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
        return texture[3];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getAnimationTexture(int meta, int side) {
        return Textures.ANIMATION;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationColor(int meta, int side) {
        return EnumXycroniumColor.values()[meta].getColorRGBA();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationBrightness(int meta, int side) {
        return 0x00F000F0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canTextureConnect(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite[] getConnectedTexture(IBlockAccess world, BlockPos pos, IBlockState state, int side) {
        return texture;
    }

}
