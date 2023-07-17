package de.germanminer.addon.v1_12_2;

import de.germanminer.addon.controller.InputPromptController;
import javax.inject.Singleton;
import net.labymod.api.models.Implements;
import net.minecraft.client.Minecraft;

@Singleton
@Implements(InputPromptController.class)
public class VersionedInputPromptController extends InputPromptController {

  @Override
  public void openInputPrompt(final String message, final String value, final String textSubmit, final String textCancel) {
    Minecraft.getMinecraft().displayGuiScreen(new TextBoxPromptScreen(message, value, textSubmit, textCancel));
  }

}
