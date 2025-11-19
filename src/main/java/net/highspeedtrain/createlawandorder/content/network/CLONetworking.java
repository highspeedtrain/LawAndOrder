package net.highspeedtrain.createlawandorder.content.network;

import java.util.Optional;

import net.highspeedtrain.createlawandorder.CreateLawAndOrder;
import net.highspeedtrain.createlawandorder.content.network.packets.CourtTerminalTextPacket;
import net.highspeedtrain.createlawandorder.content.network.packets.CourtTerminalUpdatePacket;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class CLONetworking {
    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        CreateLawAndOrder.modPath("main"), 
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = 0;

        // CourtTerminalTextPacket
        CHANNEL.registerMessage(
            id++, 
            CourtTerminalTextPacket.class, 
            CourtTerminalTextPacket::encode,
            CourtTerminalTextPacket::decode,
            CourtTerminalTextPacket::handle,
            Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );
        
        // CourtTerminalUpdatePacket
        CHANNEL.registerMessage(
            id++, 
            CourtTerminalUpdatePacket.class, 
            CourtTerminalUpdatePacket::encode, 
            CourtTerminalUpdatePacket::decode, 
            CourtTerminalUpdatePacket::handle,
            Optional.of(NetworkDirection.PLAY_TO_CLIENT)
        );
    }
}
