package net.voidblock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.voidblock.VoidBlockMod;
import net.voidblock.client.VoidBlockEntityRenderer;

public class VoidBlockClient implements ClientModInitializer {

    @Override
    @SuppressWarnings("deprecation")
    public void onInitializeClient() {
        // 1) Register the block entity renderer
        BlockEntityRendererRegistry.register(
                VoidBlockMod.VOID_BLOCK_ENTITY,
                VoidBlockEntityRenderer::new
        );

        // 2) Add the block item to the functional blocks creative tab
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
                .register(entries -> entries.accept(VoidBlockMod.VOID_BLOCK_ITEM));

        // 3) Programmatic layer registration (translucent)
        BlockRenderLayerMap.INSTANCE.putBlock(
                VoidBlockMod.VOID_BLOCK,
                RenderType.translucent()
        );

        System.out.println("Void Block client initialized!");
    }
}
