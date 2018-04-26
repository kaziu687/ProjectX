package de.kitsunealex.projectx.block;

import de.kitsunealex.projectx.api.color.EnumXycroniumColor;
import de.kitsunealex.projectx.util.Constants;
import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockXycroniumOre extends BlockAnimationHandler implements ISubtypeHolder {

    public BlockXycroniumOre() {
        super("xycronium_ore", Material.ROCK);
        setHardness(1.6F);
        setResistance(1.5F);
    }

    @Override
    public String[] getSubNames() {
        return EnumXycroniumColor.toStringArray();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {
        texture = new TextureAtlasSprite[getSubNames().length + 1];

        for(int i = 0; i < texture.length - 1; i++) {
            String path = String.format("blocks/%s/%s_%s", blockName, blockName, getSubNames()[i]);
            texture[i] = map.registerSprite(new ResourceLocation(Constants.MODID, path));
        }

        String path = String.format("blocks/%s/%s_effect", blockName, blockName);
        texture[getSubNames().length] = map.registerSprite(new ResourceLocation(Constants.MODID, path));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getAnimationTexture(int meta, int side) {
        return texture[getSubNames().length];
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

}
