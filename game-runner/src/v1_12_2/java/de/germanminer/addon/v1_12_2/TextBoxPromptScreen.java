package de.germanminer.addon.v1_12_2;

import de.germanminer.addon.GermanMinerAddon;
import de.germanminer.addon.api.protocol.packet.special.InputPromptPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.text.TextFormatting;

public class TextBoxPromptScreen extends GuiScreen {

  private final String message;
  private final String value;
  private final String textSubmit;
  private final String textCancel;
  private GuiTextField field;
  private GuiButton buttonSubmit;
  private GuiButton buttonCancel;

  public TextBoxPromptScreen(final String message, final String value,
      final String textSubmit, final String textCancel) {
    this.message = message;
    this.value = value == null ? "" : value;
    this.textSubmit = TextFormatting.WHITE + textSubmit;
    this.textCancel = TextFormatting.WHITE + textCancel;
  }

  @Override
  public void initGui() {
    super.initGui();
    super.buttonList.clear();

    final int x = super.width / 2 - 150;
    final int y = super.height / 2;

    this.field = new GuiTextField(0, Minecraft.getMinecraft().fontRenderer, x, y, 300, 20);
    this.field.setFocused(true);
    this.field.setMaxStringLength(256);
    this.field.setText(this.value);
    this.field.setCursorPositionEnd();

    this.buttonSubmit = new GuiButton(1, super.width / 2 + 20,
        super.height / 2 + 25, 90, 20, this.textSubmit);
    this.buttonCancel = new GuiButton(2, super.width / 2 - 110,
        super.height / 2 + 25, 90, 20, this.textCancel);

    super.buttonList.add(this.buttonSubmit);
    super.buttonList.add(this.buttonCancel);
  }

  @Override
  public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
    this.drawWorldBackground(75);
    this.field.setFocused(true);
    this.field.drawTextBox();
    this.buttonSubmit.enabled = !this.field.getText().isEmpty();
    this.drawCenteredMultilineString(Minecraft.getMinecraft().fontRenderer, this.message,
        super.width / 2, super.height / 2 - 25, 16777215);
    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  public void updateScreen() {
    super.updateScreen();
    this.field.updateCursorCounter();
  }

  public void drawCenteredMultilineString(final FontRenderer fontRendererIn, final String text,
      final int x, int y, final int color) {
    final String[] splitText = text.split("\n");
    final int lineHeight = fontRendererIn.FONT_HEIGHT + 5;
    y -= (splitText.length - 1) * lineHeight;

    for (final String line : splitText) {
      super.drawCenteredString(fontRendererIn, TextFormatting.YELLOW + line, x, y, color);
      y += lineHeight;
    }

  }

  @Override
  protected void actionPerformed(final GuiButton button) {
    super.actionPerformed(button);

    if (button.id == this.buttonSubmit.id) {
      GermanMinerAddon.getInstance().sendPacket(
          new InputPromptPacket(null, this.field.getText(), null, null, null));
      Minecraft.getMinecraft().displayGuiScreen(null);
    } else if (button.id == this.buttonCancel.id) {
      GermanMinerAddon.getInstance().sendPacket(
          new InputPromptPacket(null, null, null, null, true));
      Minecraft.getMinecraft().displayGuiScreen(null);
    }
  }

  @Override
  protected void keyTyped(final char typedChar, final int keyCode) {
    if (keyCode == 1) {
      Minecraft.getMinecraft().displayGuiScreen(this);
    } else if (keyCode == 28) {
      if (!this.field.getText().isEmpty()) {
        this.actionPerformed(this.buttonSubmit);
      }

    } else {
      super.keyTyped(typedChar, keyCode);
      this.field.textboxKeyTyped(typedChar, keyCode);
    }
  }

  @Override
  protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
    super.mouseClicked(mouseX, mouseY, mouseButton);
    this.field.mouseClicked(mouseX, mouseY, mouseButton);
  }

}
