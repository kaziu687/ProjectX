package de.kitsunealex.projectx.item;

import codechicken.lib.render.item.IItemRenderer;
import de.kitsunealex.projectx.ProjectX;
import de.kitsunealex.projectx.client.IAnimationHandler;
import de.kitsunealex.projectx.client.render.item.RenderXycroniumCrystal;
import de.kitsunealex.projectx.util.EnumXycroniumColor;
import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

public class ItemXycroniumCrystal extends ItemBase implements ISubtypeHolder, IAnimationHandler {

    public ItemXycroniumCrystal() {
        super("xycronium_crystal");
    }

    @Override
    public String[] getSubNames() {
        return Arrays.stream(EnumXycroniumColor.values()).map(EnumXycroniumColor::getName).toArray(String[]::new);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {}

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getAnimationTexture(int meta, int side) {
        return ProjectX.PROXY.getAnimation();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationColor(int meta, int side) {
        return EnumXycroniumColor.values()[meta].getColorRGBA();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getAnimationBrightness(int meta, int side) {
        return 220;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer() {
        return RenderXycroniumCrystal.INSTANCE;
    }

}
