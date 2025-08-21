package net.voidblock.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.voidblock.VoidBlock;
import net.voidblock.VoidBlockEntity;

public class VoidBlockEntityRenderer extends TheEndPortalRenderer<VoidBlockEntity> {

    public VoidBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(VoidBlockEntity entity,
                       float partialTick,
                       PoseStack poseStack,
                       MultiBufferSource buffers,
                       int light,
                       int overlay) {
        if (!entity.getBlockState().getValue(VoidBlock.ACTIVE)) return;
        super.render(entity, partialTick, poseStack, buffers, light, overlay);
    }

    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    protected float getOffsetUp() {
        return 1.0F;
    }

    @Override
    protected float getOffsetDown() {
        return 0.0F;
    }
}