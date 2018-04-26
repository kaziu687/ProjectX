package de.kitsunealex.projectx.block;

import codechicken.lib.block.property.PropertyInteger;
import codechicken.lib.render.item.IItemRenderer;
import codechicken.lib.texture.TextureUtils;
import de.kitsunealex.projectx.ProjectX;
import de.kitsunealex.projectx.api.client.ITextureProvider;
import de.kitsunealex.projectx.api.client.RenderTypes;
import de.kitsunealex.projectx.client.IItemRenderProvider;
import de.kitsunealex.projectx.client.render.RenderDefaultBlock;
import de.kitsunealex.projectx.item.ItemBlockBase;
import de.kitsunealex.projectx.util.Constants;
import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockBase<T extends TileEntity> extends Block implements ITileEntityProvider, TextureUtils.IIconRegister, ITextureProvider, IItemRenderProvider {

    public static final PropertyInteger METADATA = new PropertyInteger("meta", 15);
    protected String blockName;
    @SideOnly(Side.CLIENT)
    protected TextureAtlasSprite[] texture;

    public BlockBase(String blockName, Material material) {
        this(blockName, material, material.getMaterialMapColor(), ItemBlockBase.class);
    }

    public BlockBase(String blockName, Material material, Class<? extends ItemBlock> itemBlock) {
        this(blockName, material, material.getMaterialMapColor(), itemBlock);
    }

    public BlockBase(String blockName, Material material, MapColor mapColor) {
        this(blockName, material, mapColor, ItemBlockBase.class);
    }

    public BlockBase(String blockName, Material material, MapColor mapColor, Class<? extends ItemBlock> itemBlock) {
        super(material, mapColor);
        this.blockName = blockName;
        setRegistryName(Constants.MODID, blockName);
        setUnlocalizedName(String.format("%s.%s", Constants.MODID, blockName));
        setCreativeTab(ProjectX.CREATIVE_TAB);
        setDefaultState(blockState.getBaseState().withProperty(METADATA, 0));
        ForgeRegistries.BLOCKS.register(this);

        try {
            ItemBlock ibInstance = itemBlock.getConstructor(Block.class).newInstance(this);
            ibInstance.setRegistryName(getRegistryName());
            ForgeRegistries.ITEMS.register(ibInstance);
        }
        catch(Exception e) {
            ProjectX.LOGGER.error("Could not instantiate ItemBlock for block {}!", getClass().getName());
        }

        ProjectX.PROXY.registerBlockRenderer(this);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, METADATA);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(METADATA);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(METADATA, meta);
    }

    @Nullable
    @Override
    public T createNewTileEntity(World world, int meta) {
        return null;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this instanceof ISubtypeHolder ? getMetaFromState(state) : super.damageDropped(state);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        if(this instanceof ISubtypeHolder) {
            return new ItemStack(this, 1, getMetaFromState(state));
        }
        else {
            return new ItemStack(this, 1, 0);
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(tab == getCreativeTabToDisplayOn()) {
            if(this instanceof ISubtypeHolder) {
                String[] subNames = ((ISubtypeHolder)this).getSubNames();

                for(int i = 0; i < subNames.length; i++) {
                    items.add(new ItemStack(this, 1, i));
                }
            }
            else {
                items.add(new ItemStack(this, 1, 0));
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(TextureMap map) {
        if(this instanceof ISubtypeHolder) {
            String[] subNames = ((ISubtypeHolder)this).getSubNames();
            texture = new TextureAtlasSprite[subNames.length];

            for(int i = 0; i < texture.length; i++) {
                String path = String.format("blocks/%s_%s", blockName, subNames[i]);
                texture[i] = map.registerSprite(new ResourceLocation(Constants.MODID, path));
            }
        }
        else {
            texture = new TextureAtlasSprite[1];
            String path = String.format("blocks/%s", blockName);
            texture[0] = map.registerSprite(new ResourceLocation(Constants.MODID, path));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getTexture(int meta, int side) {
        return texture[meta];
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return RenderTypes.DEFAULT_BLOCK;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IItemRenderer getItemRenderer() {
        return RenderDefaultBlock.INSTANCE;
    }

}
