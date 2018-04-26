package de.kitsunealex.projectx.proxy;

import de.kitsunealex.projectx.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void handlePreInit(FMLPreInitializationEvent event) {
        ModBlocks.registerBlocks();
    }

    public void handleInit(FMLInitializationEvent event) {
        ModBlocks.registerTileEntities();
    }

    public void handlePostInit(FMLPostInitializationEvent event) {

    }

    public void registerBlockRenderer(Block block) {

    }

    public void registerItemRenderer(Item item) {

    }

}
