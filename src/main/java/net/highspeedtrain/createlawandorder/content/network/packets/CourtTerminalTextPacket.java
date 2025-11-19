package net.highspeedtrain.createlawandorder.content.network.packets;

import java.util.function.Supplier;

import net.highspeedtrain.createlawandorder.content.ui.CourtTerminalMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

public class CourtTerminalTextPacket {
    private final int containerId;
    private String text = null;

    public CourtTerminalTextPacket(int containerId, String text) {
        this.containerId = containerId;
        this.text = text;
    }

    public static void encode(CourtTerminalTextPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.containerId);
        buf.writeUtf(packet.text, 32767);
    }

    public static CourtTerminalTextPacket decode(FriendlyByteBuf buf) {
        int id = buf.readInt();
        String text = buf.readUtf(32767);
        return new CourtTerminalTextPacket(id, text);
    }

    public static void handle(CourtTerminalTextPacket packet, Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            if (ctx.getDirection() != NetworkDirection.PLAY_TO_SERVER) {
                return;
            }

            ServerPlayer player = ctx.getSender();
            CourtTerminalMenu menu = (CourtTerminalMenu)player.containerMenu;
            if (player.containerMenu.containerId != packet.containerId || !(player.containerMenu instanceof CourtTerminalMenu)) {
                return;
            }
            if (packet.text != null) {
                menu.setPlayerText(packet.text, player.level());
            }
        });
        ctx.setPacketHandled(true);
    }
}
