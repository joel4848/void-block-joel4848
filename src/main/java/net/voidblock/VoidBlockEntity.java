package net.voidblock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public class VoidBlockEntity extends TheEndPortalBlockEntity {

    public VoidBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(VoidBlockMod.VOID_BLOCK_ENTITY, blockPos, blockState);
    }

    private final Boolean[] shouldRender = new Boolean[6];

    public boolean shouldRenderFace(Direction direction) {
        int index = direction.ordinal();

        if (shouldRender[index] == null) {
            shouldRender[index] = level == null || Block.shouldRenderFace(getBlockState(), level, getBlockPos(), direction, getBlockPos().relative(direction));
        }

        return shouldRender[index];
    }

    public void neighborChanged() {
        Arrays.fill(shouldRender, null);
    }
}
