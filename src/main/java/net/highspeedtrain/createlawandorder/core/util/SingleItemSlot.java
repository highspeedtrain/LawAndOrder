package net.highspeedtrain.createlawandorder.core.util;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.Slot;

public class SingleItemSlot extends Slot {
    public SingleItemSlot(CraftingContainer container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }
}
