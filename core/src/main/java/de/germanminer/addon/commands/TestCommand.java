package de.germanminer.addon.commands;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.event.ClickEvent;
import net.labymod.serverapi.protocol.api.ProtocolApi;
import net.labymod.serverapi.protocol.api.ProtocolApiBridge;
import net.labymod.serverapi.protocol.packet.Packet;
import net.labymod.serverapi.protocol.packet.protocol.Protocol;
import net.labymod.serverapi.protocol.packet.protocol.ProtocolService;
import net.labymod.serverapi.protocol.packet.protocol.neo.game.supplement.ServerEventPacket;
import net.labymod.serverapi.protocol.packet.protocol.neo.login.VersionLoginPacket;
import net.labymod.serverapi.protocol.payload.identifier.PayloadChannelIdentifier;
import net.labymod.serverapi.protocol.payload.io.PayloadWriter;
import org.jetbrains.annotations.NotNull;
import java.nio.charset.StandardCharsets;
import java.rmi.ServerError;
import java.util.Map.Entry;

public class TestCommand extends Command {

  public TestCommand() {
    super("test", "test");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    ServerEventPacket serverEventPacket = new ServerEventPacket();
    serverEventPacket.setType("INFO");

    ProtocolApiBridge.getProtocolApi().getProtocolService().getNeoProtocol().sendPacket(serverEventPacket);
    this.displayMessage(Component.text("").clickEvent(ClickEvent.runCommand("")));
    return true;
  }

  private byte[] writePacketToBinary(@NotNull Packet packet) {
    PayloadWriter writer = new PayloadWriter();
    int packetId = 70;

    writer.writeVarInt(packetId);
    writer.writeString(new GsonBuilder().create().toJson(packet));

    return writer.toByteArray();
  }

}