package de.kitsunealex.projectx.client;

import codechicken.lib.render.item.IItemRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemRenderProvider {

    @SideOnly(Side.CLIENT)
    IItemRenderer getItemRenderer();

}
