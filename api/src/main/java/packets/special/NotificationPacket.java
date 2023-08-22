package packets.special;

import packets.GermanMinerPacket;

/**
 * Packet zum Senden / Empfangen von Benachrichtigungen
 */
public class NotificationPacket implements GermanMinerPacket {

  private String title;
  private String message;
  private String uuid;
  private String url;

  public NotificationPacket() {
  }

  public NotificationPacket(String title, String message, String uuid, String url) {
    this.title = title;
    this.message = message;
    this.uuid = uuid;
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public String getMessage() {
    return message;
  }

  public String getUuid() {
    return uuid;
  }

  public String getUrl() {
    return url;
  }
}