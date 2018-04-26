package de.kitsunealex.projectx.api.client;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ICTMBlock {

    @SideOnly(Side.CLIENT)
    TextureAtlasSprite[] getConnectedTexture(IBlockAccess world, BlockPos pos, IBlockState state, int side);

    @SideOnly(Side.CLIENT)
    default TextureAtlasSprite[] registerConnectedTexture(TextureMap map, ResourceLocation location) {
        TextureAtlasSprite[] textures = new TextureAtlasSprite[5];
        String domain = location.getResourceDomain();
        String path = location.getResourcePath();
        textures[0] = map.registerSprite(new ResourceLocation(domain, String.format("%s_c", path)));
        textures[1] = map.registerSprite(new ResourceLocation(domain, String.format("%s_v", path)));
        textures[2] = map.registerSprite(new ResourceLocation(domain, String.format("%s_h", path)));
        textures[3] = map.registerSprite(location);
        textures[4] = map.registerSprite(new ResourceLocation(domain, String.format("%s_a", path)));
        return textures;
    }

}
