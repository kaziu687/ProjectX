package de.kitsunealex.projectx.client.render;

import codechicken.lib.vec.Vector3;
import codechicken.lib.vec.Vertex5;
import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ConnectedRenderContext {

    private static final ThreadLocal<ConnectedRenderContext> INSTANCE = ThreadLocal.withInitial(ConnectedRenderContext::new);
    private static final double[] U = {-1D, 1D, 1D, -1D};
    private static final double[] V = {1D, 1D, -1D, -1D};
    private static final Vector3[] OFFSET = {
            new Vector3(0D, -0.0001D, 0D),
            new Vector3(0D, 0.0001D, 0D),
            new Vector3(0D, 0D, -0.0001D),
            new Vector3(0D, 0D, 0.0001D),
            new Vector3(-0.0001D, 0D, 0D),
            new Vector3(0.0001D, 0D, 0D)
    };
    private static final Vector3[] POSITIONS_O = {
            new Vector3(0.5D, 0D, 0.5D),
            new Vector3(0.5D, 1D, 0.5D),
            new Vector3(0.5D, 0.5D, 0D),
            new Vector3(0D, 0.5D, 0.5D),
            new Vector3(1D, 0.5D, 0.5D),
            new Vector3(0.5D, 0.5D, 1D)
    };
    private static final Vector3[] POSITIONS_A = {
            new Vector3(-1D, 0D, 0D),
            new Vector3(-1D, 0D, 0D),
            new Vector3(1D, 0D, 0D),
            new Vector3(0D, 0D, -1D),
            new Vector3(0D, 0D, 1D),
            new Vector3(-1D, 0D, 0D)
    };
    private static final Vector3[] POSITIONS_B = {
            new Vector3(0D, 0D, 1D),
            new Vector3(0D, 0D, -1D),
            new Vector3(0D, 1D, 0D),
            new Vector3(0D, 1D, 0D),
            new Vector3(0D, 1D, 0D),
            new Vector3(0D, 1D, 0D)
    };
    private static final Vector3[] POSITIONS_R = {
            new Vector3(0.5D, 1D, 0.5D),
            new Vector3(0.5D, 0D, 0.5D),
            new Vector3(0.5D, 0.5D, 1D),
            new Vector3(1D, 0.5D, 0.5D),
            new Vector3(0D, 0.5D, 0.5D),
            new Vector3(0.5D, 0.5D, 0D)
    };
    private IBlockAccess world;
    private IBlockState matchState;
    private boolean changeBounds = false;
    private Vector3 offset = Vector3.zero;

    @Nonnull
    private List<Vertex5> renderSide(@Nonnull Vector3 pos, @Nonnull TextureAtlasSprite[] textures, @Nonnull EnumFacing side) {
        byte[] bitmask = new byte[4];
        boolean areSame = true;
        Vector3 posO = POSITIONS_O[side.getIndex()].copy().multiply(2D).subtract(1D);
        Vector3 posA = POSITIONS_A[side.getIndex()];
        Vector3 posB = POSITIONS_B[side.getIndex()];
        Vector3 posR = POSITIONS_R[side.getIndex()];
        List<Vertex5> vertices = Lists.newArrayList();

        for(int i = 0; i < 4; i++) {
            bitmask[i] = getType(new Vector3[]{pos, posA.copy().multiply(U[i]), posB.copy().multiply(V[i]), posO}, side);

            if(areSame && i > 0 && (bitmask[i] != bitmask[0])) {
                areSame = false;
            }
        }

        if(areSame) {
            for(int i = 0; i < 4; i++) {
                double cx = posR.x + posO.x + U[i] * posA.x * 0.5D + V[i] * posB.x * 0.5D;
                double cy = posR.y + posO.y + U[i] * posA.y * 0.5D + V[i] * posB.y * 0.5D;
                double cz = posR.z + posO.z + U[i] * posA.z * 0.5D + V[i] * posB.z * 0.5D;

                if(changeBounds) {
                    offset = OFFSET[side.getIndex()];
                }

                double u = textures[bitmask[0]].getInterpolatedU(16D - (8D + U[i] * 8D));
                double v = textures[bitmask[0]].getInterpolatedV(16D - (8D + V[i] * 8D));
                vertices.add(new Vertex5(new Vector3(cx, cy, cz).add(offset), u, v));
            }

            return vertices;
        }

        for(int i = 0; i < 4; i++) {
            double cx = posR.x + posO.x + posA.x * U[i] / 4D + posB.x * V[i] / 4D;
            double cy = posR.y + posO.y + posA.y * U[i] / 4D + posB.y * V[i] / 4D;
            double cz = posR.z + posO.z + posA.z * U[i] / 4D + posB.z * V[i] / 4D;

            if(changeBounds) {
                offset = OFFSET[side.getIndex()];
            }

            for(int j = 0; j < 4; j++) {
                double x = cx + U[j] * posA.x * 0.25D + V[j] * posB.x * 0.25D + offset.x;
                double y = cy + U[j] * posA.y * 0.25D + V[j] * posB.y * 0.25D + offset.y;
                double z = cz + U[j] * posA.z * 0.25D + V[j] * posB.z * 0.25D + offset.z;
                double u = textures[bitmask[i]].getInterpolatedU(16D - (8D + U[i] * 4D + U[j] * 4D));
                double v = textures[bitmask[i]].getInterpolatedV(16D - (8D + V[i] * 4D + V[j] * 4D));
                vertices.add(new Vertex5(x, y, z, u, v));
            }
        }

        return vertices;
    }

    private byte getType(Vector3[] positions, EnumFacing side) {
        Vector3 pos = positions[0];
        Vector3 posA = positions[1];
        Vector3 posB = positions[2];
        Vector3 posC = positions[3];

        //Type 2
        if((matchBlock(pos.copy().add(posA)) && !matchBlock(pos.copy().add(posC))) && !matchBlock(pos.copy().add(posA).add(posC))) {
            //Type 4
            if((matchBlock(pos.copy().add(posB)) && !matchBlock(pos.copy().add(posC))) && !matchBlock(pos.copy().add(posB).add(posC))) {
                //Type 3
                if(matchBlock(pos.copy().add(posA).add(posB))) {
                    //Type 4
                    if(matchBlock(pos.copy().add(posA).add(posB).add(posC)) || matchBlock(pos.copy().add(posB).add(posC)) || matchBlock(pos.copy().add(posA).add(posC))) {
                        return 4;
                    }

                    return 3;
                }

                return 4;
            }

            return 2;
        }

        //Type 1
        if((matchBlock(pos.copy().add(posB)) && !matchBlock(pos.copy().add(posC))) && !matchBlock(pos.copy().add(posB).add(posC))) {
            return 1;
        }

        return 0;
    }

    private boolean matchBlock(Vector3 pos) {
        return world.getBlockState(pos.pos()) == matchState;
    }

    public void setWorld(@Nonnull IBlockAccess world) {
        this.world = world;
    }

    public void setMatchState(@Nonnull IBlockState state) {
        matchState = state;
    }

    public void reset() {
        world = null;
        matchState = null;
        offset = Vector3.zero;
    }

    @Nonnull
    public Vector3 getOffset() {
        return offset;
    }

    public static ConnectedRenderContext getInstance() {
        return INSTANCE.get();
    }

}
