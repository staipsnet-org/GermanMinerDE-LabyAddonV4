package packets.special;

import com.google.gson.annotations.SerializedName;
import packets.GermanMinerPacket;

/**
 * Packet zum Senden / Empfangen eines Eingabefeldes (z.B. Banksystem)
 */
public class InputPromptPacket implements GermanMinerPacket {

  @SerializedName("button_submit")
  private String buttonSubmit;
  @SerializedName("button_cancel")
  private String buttonCancel;
  private String message;
  private String value;
  private Boolean exit;

  public InputPromptPacket() {
  }

  public InputPromptPacket(String message, String value, String submitButton, String cancelButton,
      Boolean exit) {
    this.message = message;
    this.value = value;
    this.buttonSubmit = submitButton;
    this.buttonCancel = cancelButton;
    this.exit = exit;
  }

  public InputPromptPacket(String value) {
    this.value = value;
  }

  public InputPromptPacket(Boolean exit) {
    this.exit = exit;
  }

  public String getMessage() {
    return message;
  }

  public String getValue() {
    return value;
  }

  public String getButtonSubmit() {
    return buttonSubmit;
  }

  public String getButtonCancel() {
    return buttonCancel;
  }
}