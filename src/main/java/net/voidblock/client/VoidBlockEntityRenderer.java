package net.voidblock.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;
import org.joml.Matrix3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.voidblock.VoidBlock;
import net.voidblock.VoidBlockEntity;

public class VoidBlockEntityRenderer implements BlockEntityRenderer<VoidBlockEntity> {

    public VoidBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(VoidBlockEntity entity,
                       float partialTick,
                       PoseStack poseStack,
                       MultiBufferSource buffers,
                       int light,
                       int overlay) {
        if (!entity.getBlockState().getValue(VoidBlock.ACTIVE)) return;

        // Try endGateway first for better shader compatibility
        VertexConsumer vb = buffers.getBuffer(RenderType.endGateway());
        renderCube(poseStack, vb, light, overlay);
    }

    private void renderCube(PoseStack poseStack,
                            VertexConsumer vb,
                            int light,
                            int overlay) {
        Matrix4f mat = poseStack.last().pose();

        // +Z face (SOUTH)
        renderFace(mat, vb, light, overlay,
                0f, 1f, 0f, 1f, 1f, 1f, 1f, 1f, Direction.SOUTH);
        // –Z face (NORTH)
        renderFace(mat, vb, light, overlay,
                0f, 1f, 1f, 0f, 0f, 0f, 0f, 0f, Direction.NORTH);
        // +X face (EAST)
        renderFace(mat, vb, light, overlay,
                1f, 1f, 1f, 0f, 0f, 1f, 1f, 0f, Direction.EAST);
        // –X face (WEST)
        renderFace(mat, vb, light, overlay,
                0f, 0f, 0f, 1f, 0f, 1f, 1f, 0f, Direction.WEST);
        // –Y face (DOWN)
        renderFace(mat, vb, light, overlay,
                0f, 1f, 0f, 0f, 0f, 0f, 1f, 1f, Direction.DOWN);
        // +Y face (UP)
        renderFace(mat, vb, light, overlay,
                0f, 1f, 1f, 1f, 1f, 1f, 0f, 0f, Direction.UP);
    }

    private void renderFace(Matrix4f mat,
                            VertexConsumer vb,
                            int light,
                            int overlay,
                            float x0, float x1,
                            float y0, float y1,
                            float z0, float z1,
                            float z2, float z3,
                            Direction dir) {
        // tint & UVs
        float r = 1f, g = 1f, b = 1f, a = 1f;
        float u0 = 0f, v0 = 0f, u1 = 1f, v1 = 1f;
        // normal
        float nx = dir.getStepX();
        float ny = dir.getStepY();
        float nz = dir.getStepZ();

        // Check if this face should render (only render faces that are exposed)
        // This is a simplified version - you might want to add proper face culling

        // bottom-left
        vb.addVertex(mat, x0, y0, z0)
                .setColor(r, g, b, a)
                .setUv(u0, v1)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(nx, ny, nz);

        // bottom-right
        vb.addVertex(mat, x1, y0, z1)
                .setColor(r, g, b, a)
                .setUv(u1, v1)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(nx, ny, nz);

        // top-right
        vb.addVertex(mat, x1, y1, z2)
                .setColor(r, g, b, a)
                .setUv(u1, v0)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(nx, ny, nz);

        // top-left
        vb.addVertex(mat, x0, y1, z3)
                .setColor(r, g, b, a)
                .setUv(u0, v0)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(nx, ny, nz);
    }

    @Override
    public int getViewDistance() {
        return 256;
    }
}