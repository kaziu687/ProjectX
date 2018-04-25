package de.kitsunealex.projectx.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy {

    @Override
    public void handlePreInit(FMLPreInitializationEvent event) {
        super.handlePreInit(event);
    }

    @Override
    public void handleInit(FMLInitializationEvent event) {
        super.handleInit(event);
    }

    @Override
    public void handlePostInit(FMLPostInitializationEvent event) {
        super.handlePostInit(event);
    }

    @Override
    public void registerBlockRenderer(Block block) {
        super.registerBlockRenderer(block);
    }

    @Override
    public void registerItemRenderer(Item item) {
        super.registerItemRenderer(item);
    }

}
