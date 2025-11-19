package net.highspeedtrain.createlawandorder.content.network.packets;

import java.util.function.Supplier;

import net.highspeedtrain.createlawandorder.content.ui.CourtTerminalScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class CourtTerminalUpdatePacket {
    private final int containerId;
    private final boolean isValid;
    private final String error;

    public CourtTerminalUpdatePacket(int containerId, boolean isValid, String error) {
        this.containerId = containerId;
        this.isValid = isValid;
        this.error = error;
    }

    public static void encode(CourtTerminalUpdatePacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.containerId);
        buf.writeBoolean(packet.isValid);
        buf.writeUtf(packet.error);
    }

    public static CourtTerminalUpdatePacket decode(FriendlyByteBuf buf) {
        return new CourtTerminalUpdatePacket(
            buf.readInt(), 
            buf.readBoolean(), 
            buf.readUtf()
        );
    }

    public static void handle(CourtTerminalUpdatePacket packet, Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();

        ctx.enqueueWork(() -> {
            if (ctx.getDirection() != NetworkDirection.PLAY_TO_CLIENT) {
                return;
            }

            Screen screen = Minecraft.getInstance().screen;
            if (screen instanceof CourtTerminalScreen cts && cts.getMenu().containerId == packet.containerId) {
                cts.isValid = packet.isValid;
                cts.error = packet.error;
                cts.setFocused(null);
            }
        });
        ctx.setPacketHandled(true);
    }
}