package de.germanminer.addon.protocol.handler;

import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.notification.Notification;
import net.labymod.serverapi.protocol.packet.PacketHandler;
import packets.special.NotificationPacket;

import java.util.UUID;

/**
 * Handler zum Verarbeiten der Benachrichtigungen
 */
public class NotificationPacketHandler implements PacketHandler<NotificationPacket> {

  @Override
  public void handle(NotificationPacket packet) {
    if (packet.getTitle() == null || packet.getMessage() == null) {
      return;
    }

    Notification notification;

    if (packet.getUrl() != null) {
      notification = Notification.builder()
          .icon(Icon.url(packet.getUrl()))
          .title(Component.text(packet.getTitle()))
          .text(Component.text(packet.getMessage()))
          .build();
    } else if (packet.getUuid() != null) {
      notification = Notification.builder()
          .icon(Icon.head(UUID.fromString(packet.getUuid())))
          .title(Component.text(packet.getTitle()))
          .text(Component.text(packet.getMessage()))
          .build();
    } else {
      notification = Notification.builder()
          .title(Component.text(packet.getTitle()))
          .text(Component.text(packet.getMessage()))
          .build();
    }

    Laby.labyAPI().notificationController().push(notification);
  }
}
