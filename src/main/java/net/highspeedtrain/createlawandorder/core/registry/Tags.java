package net.highspeedtrain.createlawandorder.core.registry;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Tags {
    public static class Blocks {
        public static final TagKey<Block> CAN_WIRE_BLOCK = tag("can_wire_block");
        public static final TagKey<Block> NEEDS_PICKAXE = tag("needs_pickaxe");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(CreateLawAndOrder.modPath(name));
        }
    }

    public static class Items {
        @SuppressWarnings({ "unused" })
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(CreateLawAndOrder.modPath(name));
        }
    }
}
