package net.highspeedtrain.createlawandorder.core.registry;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Tags {
    public static class Blocks {
        public static final TagKey<Block> CAN_WIRE_BLOCK = tag("can_wire_block");
        public static final TagKey<Block> NEEDS_PICKAXE = tag("needs_pickaxe");
        public static final TagKey<Block> NEEDS_IRON_TOOL = tag("needs_iron_tool");

        @SuppressWarnings("removal")
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(CreateLawAndOrder.MOD_ID, name));
        }
    }

    public static class Items {
        @SuppressWarnings({ "removal", "unused" })
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(CreateLawAndOrder.MOD_ID, name));
        }
    }
}
