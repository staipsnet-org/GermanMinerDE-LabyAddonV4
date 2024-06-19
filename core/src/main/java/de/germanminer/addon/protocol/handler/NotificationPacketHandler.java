package de.germanminer.addon.protocol.handler;

import de.germanminer.addon.api.protocol.packet.miscellaneous.NotificationPacket;
import java.util.UUID;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.notification.Notification;
import net.labymod.serverapi.api.packet.PacketHandler;
import org.jetbrains.annotations.NotNull;

public class NotificationPacketHandler implements PacketHandler<NotificationPacket> {

  @Override
  public void handle(@NotNull final UUID sender, @NotNull final NotificationPacket packet) {
    if (packet.getTitle() == null || packet.getMessage() == null) {
      return;
    }

    final Notification notification;

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
