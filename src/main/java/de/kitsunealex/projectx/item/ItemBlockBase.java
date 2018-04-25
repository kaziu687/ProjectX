package de.kitsunealex.projectx.item;

import de.kitsunealex.projectx.util.ISubtypeHolder;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBlockBase extends ItemBlock {

    public ItemBlockBase(Block block) {
        super(block);
        setHasSubtypes(block instanceof ISubtypeHolder);
    }

    @Override
    public int getMetadata(int damage) {
        return block instanceof ISubtypeHolder ? damage : super.getMetadata(damage);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        block.getSubBlocks(tab, items);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getUnlocalizedName(ItemStack stack) {
        if(block instanceof ISubtypeHolder) {
            String[] subNames = ((ISubtypeHolder)block).getSubNames();
            return String.format("%s.%s", getUnlocalizedName(), subNames[stack.getMetadata()]);
        }
        else {
            return getUnlocalizedName();
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        block.addInformation(stack, world, tooltip, flag);
    }

}
