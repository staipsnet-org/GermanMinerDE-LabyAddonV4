package de.germanminer.addon.api.protocol.packet.miscellaneous;

import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class NotificationPacket implements GermanMinerPacket {

  private String title;
  private String message;
  private String uuid;
  private String url;

  public NotificationPacket() {
  }

  public NotificationPacket(final String title, final String message,
      final String uuid, final String url) {
    this.title = title;
    this.message = message;
    this.uuid = uuid;
    this.url = url;
  }

  public String getTitle() {
    return this.title;
  }

  public String getMessage() {
    return this.message;
  }

  public String getUuid() {
    return this.uuid;
  }

  public String getUrl() {
    return this.url;
  }

}
