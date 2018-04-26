package de.kitsunealex.projectx.block;

import de.kitsunealex.projectx.api.client.IColorProvider;
import de.kitsunealex.projectx.api.color.EnumXycroniumColor;
import de.kitsunealex.projectx.util.Constants;
import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfusedBricks extends BlockBase implements ISubtypeHolder, IColorProvider {

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite texture;

    public BlockInfusedBricks() {
        super("infused_bricks", Material.ROCK);
        setHardness(1.3F);
        setResistance(1.5F);
    }

    @Override
    public String[] getSubNames() {
        return EnumXycroniumColor.toStringArray();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {
        String path = String.format("blocks/%s", blockName);
        texture = map.registerSprite(new ResourceLocation(Constants.MODID, path));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta, int side) {
        return texture;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorMultiplier(int meta, int side) {
        return EnumXycroniumColor.values()[meta].getColorRGBA();
    }

}
