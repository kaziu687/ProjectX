package de.kitsunealex.projectx.util;

import de.kitsunealex.projectx.client.render.SimpleBakedModel;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class RenderUtils {

    public static boolean renderQuads(List<BakedQuad> quads, IBlockAccess world, BlockPos pos, IBlockState state, BufferBuilder buffer) {
        boolean ambientOcclusion = Minecraft.isAmbientOcclusionEnabled();
        BlockModelRenderer renderer = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer();
        IBakedModel model = new SimpleBakedModel(quads);
        long random = MathHelper.getPositionRandom(new Vec3i(pos.getX(), pos.getY(), pos.getZ()));

        if(ambientOcclusion) {
            return renderer.renderModelSmooth(world, model, state, pos, buffer, true, random);
        }
        else {
            return renderer.renderModelFlat(world, model, state, pos, buffer, true, random);
        }
    }

    public static String getStateKey(IBlockState state) {
        StringBuilder builder = new StringBuilder();
        builder.append(state.getBlock().getRegistryName().toString());

        for(Map.Entry<IProperty<?>, Comparable<?>> property : state.getProperties().entrySet()) {
            builder.append('|');
            builder.append(property.getKey().getName());
            builder.append('=');
            builder.append(property.getValue());
        }

        return builder.toString();
    }

}
