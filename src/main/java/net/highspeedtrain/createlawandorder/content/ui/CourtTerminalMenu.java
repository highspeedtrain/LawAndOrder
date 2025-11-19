package net.highspeedtrain.createlawandorder.content.ui;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.content.network.CLONetworking;
import net.highspeedtrain.createlawandorder.content.network.packets.CourtTerminalUpdatePacket;
import net.highspeedtrain.createlawandorder.core.registry.BlockRegistry;
import net.highspeedtrain.createlawandorder.core.registry.MenuRegistry;
import net.highspeedtrain.createlawandorder.core.util.SingleItemSlot;
import net.minecraft.locale.Language;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkDirection;

public class CourtTerminalMenu extends AbstractContainerMenu {
    public static final int DIAMOND_SLOT = 0;
    public static final int LAWSUIT_PAPER_SLOT = 1;
    public static final int RESULT_SLOT = 2;

    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 2, 1);
    private final ResultContainer resultSlots = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;
    private String selectedPlayer = null;

    private boolean lastIsValid = false;
    private String lastError = "a";

    public CourtTerminalMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public CourtTerminalMenu(int containerId, Inventory inventory, FriendlyByteBuf byteBuf) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public CourtTerminalMenu(int containerId, Inventory inventory, ContainerLevelAccess containerLevelAccess) {
        super(MenuRegistry.COURT_TERMINAL_MENU.get(), containerId);
        this.access = containerLevelAccess;
        this.player = inventory.player;
        this.addSlot(new SingleItemSlot(craftSlots, DIAMOND_SLOT, 8, 16));
        this.addSlot(new SingleItemSlot(craftSlots, LAWSUIT_PAPER_SLOT, 26, 16));
        this.addSlot(new ResultSlot(inventory.player, this.craftSlots, this.resultSlots, RESULT_SLOT, 152, 16));

        for (int row = 0; row < 3; row++) {
            for (int slot = 0; slot < 9; slot++) {
                this.addSlot(new Slot(inventory, slot + row * 9 + 9, 8 + slot * 18, 46 + row * 18));
            }
        }

        for (int slot = 0; slot < 9; slot++) {
            this.addSlot(new Slot(inventory, slot, 8 + slot * 18, 104));
        }

        update();
    }

    public boolean doesPlayerExist(String playerName) {
        if (player.level().isClientSide()) {
            CreateLawAndOrder.LOGGER.info("CourtTerminalMenu: doesPlayerExist called on client!");
            return false;
        }

        if (selectedPlayer == null) {
            return false;
        }

        if (!this.player.level().isClientSide()) {
            return this.player.level().players().stream()
                .anyMatch(player -> player.getGameProfile().getName().toLowerCase().equals(playerName));
        }

        return this.player.getName().getString().equals(playerName);
    }

    public boolean isValid() {
        if (player.level().isClientSide()) {
            CreateLawAndOrder.LOGGER.info("CourtTerminalMenu: isValid called on client!");
            return false;
        }

        ItemStack diamondStack = craftSlots.getItem(DIAMOND_SLOT);
        ItemStack lawsuitPaperStack = craftSlots.getItem(LAWSUIT_PAPER_SLOT);

        if (diamondStack.isEmpty() || lawsuitPaperStack.isEmpty() || diamondStack.getItem() != Items.DIAMOND || lawsuitPaperStack.getItem() != Items.PAPER) {
            return false;
        }

        if (selectedPlayer == null) {
            return false;
        }

        if (selectedPlayer.equals(player.getGameProfile().getName().toLowerCase())) {
            return false;
        }

        if (!doesPlayerExist(selectedPlayer)) {
            return false;
        }
        
        return true;
    }

    public String errorMessage() {
        if (player.level().isClientSide()) {
            CreateLawAndOrder.LOGGER.info("CourtTerminalMenu: errorMessage called on client!");
            return "";
        }

        ItemStack diamondStack = craftSlots.getItem(DIAMOND_SLOT);
        ItemStack lawsuitPaperStack = craftSlots.getItem(LAWSUIT_PAPER_SLOT);

        if (diamondStack.isEmpty() || lawsuitPaperStack.isEmpty() || diamondStack.getItem() != Items.DIAMOND || lawsuitPaperStack.getItem() != Items.PAPER) {
            return Language.getInstance().getOrDefault("createlawandorder.courtterminal.ui.material");
        } else if (selectedPlayer == null) {
            return Language.getInstance().getOrDefault("createlawandorder.courtterminal.ui.player");
        } else if (selectedPlayer.equals(player.getGameProfile().getName().toLowerCase())) {
            return Language.getInstance().getOrDefault("createlawandorder.courtterminal.ui.sameplayer");
        } else if (!doesPlayerExist(selectedPlayer)) {
            return Language.getInstance().getOrDefault("createlawandorder.courtterminal.ui.player");
        } else {
            return "";
        }
    }

    public void update() {
        if (player.level().isClientSide()) {
            return;
        }

        boolean currentValid = isValid();
        String currentError = errorMessage();

        if (currentValid != lastIsValid || !currentError.equals(lastError)) {
            lastIsValid = currentValid;
            lastError = currentError;

            CLONetworking.CHANNEL.sendTo(
                new CourtTerminalUpdatePacket(this.containerId, lastIsValid, lastError), 
                ((ServerPlayer)player).connection.connection,
                NetworkDirection.PLAY_TO_CLIENT
            );
        }
    }

    public void forceUpdate() {
        lastIsValid = isValid();
        lastError = errorMessage();

        CLONetworking.CHANNEL.sendTo(
            new CourtTerminalUpdatePacket(this.containerId, lastIsValid, lastError), 
            ((ServerPlayer)player).connection.connection,
            NetworkDirection.PLAY_TO_CLIENT
        );
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack moved = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            moved = stack.copy();

            if (3 > index) {
                if (!this.moveItemStackTo(stack, 3, 39, false)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, moved);
            } else {
                if (!this.moveItemStackTo(stack, 0, 2, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return moved;
    }

    public ItemStack getDiamondStack() {
        return craftSlots.getItem(DIAMOND_SLOT);
    }

    public ItemStack getLawsuitPaperStack() {
        return craftSlots.getItem(LAWSUIT_PAPER_SLOT);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, BlockRegistry.COURT_TERMINAL.get());
    }

    public void setPlayerText(String string, net.minecraft.world.level.Level level) {
        if (player.level().isClientSide()) {
            return;
        }
        selectedPlayer = string.toLowerCase();
        update();
    }

    @Override
    public void slotsChanged(Container inventory) {
        super.slotsChanged(craftSlots);
        update();
    }
}
