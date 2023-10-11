package de.germanminer.addon.api.protocol.packet.miscellaneous;

import com.google.gson.annotations.SerializedName;
import de.germanminer.addon.api.protocol.packet.GermanMinerPacket;

public class InputPromptPacket implements GermanMinerPacket {

  private String message;
  private String value;
  @SerializedName("button_submit")
  private String buttonSubmit;
  @SerializedName("button_cancel")
  private String buttonCancel;
  private Boolean exit;

  public InputPromptPacket() {
  }

  public InputPromptPacket(final String message, final String value,
      final String buttonSubmit, final String buttonCancel, final Boolean exit) {
    this.message = message;
    this.value = value;
    this.buttonSubmit = buttonSubmit;
    this.buttonCancel = buttonCancel;
    this.exit = exit;
  }

  public InputPromptPacket(final String value) {
    this.value = value;
  }

  public InputPromptPacket(final Boolean exit) {
    this.exit = exit;
  }

  public String getMessage() {
    return this.message;
  }

  public String getValue() {
    return this.value;
  }

  public String getButtonSubmit() {
    return this.buttonSubmit;
  }

  public String getButtonCancel() {
    return this.buttonCancel;
  }

  public Boolean getExit() {
    return this.exit;
  }

}
