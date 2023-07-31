package de.germanminer.addon.v1_12_2;

import de.germanminer.addon.controller.InputPromptController;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;
import javax.inject.Singleton;

@Singleton
@Implements(InputPromptController.class)
public class VersionedInputPromptController extends InputPromptController {

  @Override
  public void openInputPrompt(String message, String value, String submitText, String cancelText) {
    Minecraft.getMinecraft().displayGuiScreen(new TextBoxPromptScreen(message, value, submitText, cancelText));
  }
}
