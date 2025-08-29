package net.voidblock;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.voidblock.config.VoidBlockConfig;

public class VoidBlockMod implements ModInitializer {
    public static final String MOD_ID = "voidblock";

    public static final Block VOID_BLOCK = new VoidBlock(FabricBlockSettings.create()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(1.5F, 6.0F));

    public static final BlockEntityType<VoidBlockEntity> VOID_BLOCK_ENTITY = BlockEntityType.Builder
            .of(VoidBlockEntity::new, VOID_BLOCK)
            .build(null);

    public static final Item VOID_BLOCK_ITEM = new BlockItem(VOID_BLOCK, new Item.Properties());

    @Override
    public void onInitialize() {
        // Load config first
        VoidBlockConfig config = VoidBlockConfig.getInstance();

        // Register block
        Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID, "void_block"), VOID_BLOCK);

        // Register block entity
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "void_block"), VOID_BLOCK_ENTITY);

        // Register item
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, "void_block"), VOID_BLOCK_ITEM);

        System.out.println("Void Block mod initialized! Redstone control: " + (config.enableRedstoneControl ? "enabled" : "disabled"));
    }
}