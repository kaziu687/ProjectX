package de.kitsunealex.projectx.proxy;

import codechicken.lib.model.DummyBakedModel;
import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.render.block.BlockRenderingRegistry;
import codechicken.lib.texture.TextureUtils;
import de.kitsunealex.projectx.api.client.RenderTypes;
import de.kitsunealex.projectx.client.IItemRenderProvider;
import de.kitsunealex.projectx.client.render.RenderDefaultBlock;
import de.kitsunealex.projectx.client.render.RenderDefaultBlockGlow;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    private static int renderID = 0;

    @Override
    public void handlePreInit(FMLPreInitializationEvent event) {
        super.handlePreInit(event);
        RenderTypes.DEFAULT_BLOCK = getNextAvailableType();
        BlockRenderingRegistry.registerRenderer(RenderTypes.DEFAULT_BLOCK, RenderDefaultBlock.INSTANCE);
        RenderTypes.DEFAULT_BLOCK_GLOW = getNextAvailableType();
        BlockRenderingRegistry.registerRenderer(RenderTypes.DEFAULT_BLOCK_GLOW, RenderDefaultBlockGlow.INSTANCE);
        RenderTypes.BEVELED_BLOCK_GLOW = getNextAvailableType();
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

        if(block instanceof TextureUtils.IIconRegister) {
            TextureUtils.addIconRegister((TextureUtils.IIconRegister)block);
        }

        StateMap.Builder builder = new StateMap.Builder();
        block.getBlockState().getProperties().forEach(builder::ignore);
        ModelLoader.setCustomStateMapper(block, builder.build());
        ModelResourceLocation location = new ModelResourceLocation(block.getRegistryName(), "normal");
        ModelRegistryHelper.register(location, new DummyBakedModel());

        if(block instanceof IItemRenderProvider) {
            Item item = Item.getItemFromBlock(block);
            IItemRenderProvider provider = (IItemRenderProvider)block;
            ModelResourceLocation itemLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
            ModelLoader.setCustomMeshDefinition(item, stack -> itemLocation);
            ModelRegistryHelper.registerItemRenderer(item, provider.getItemRenderer());
        }
    }

    @Override
    public void registerItemRenderer(Item item) {
        super.registerItemRenderer(item);
    }

    private EnumBlockRenderType getNextAvailableType() {
        return BlockRenderingRegistry.createRenderType(String.format("brh_%d", renderID++));
    }

}
