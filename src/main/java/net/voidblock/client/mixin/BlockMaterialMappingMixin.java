package net.voidblock.client.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.voidblock.VoidBlockMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(targets = "net.irisshaders.iris.shaderpack.materialmap.BlockMaterialMapping")
public class BlockMaterialMappingMixin {
    @WrapOperation(method = "addBlockStates", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/objects/Object2IntMap;putIfAbsent(Ljava/lang/Object;I)I"), remap = false)
    private static int voidblock$addVoidblock(Object2IntMap<BlockState> instance, Object state, int intId, Operation<Integer> original) {
        // mixin into iris block mapping, do add the VOID_BLOCK to the same id as the END_PORTAL
        if (((BlockState) state).is(Blocks.END_PORTAL)) {
            original.call(instance, VoidBlockMod.VOID_BLOCK.defaultBlockState(), intId);
        }
        return original.call(instance, state, intId);
    }
}
