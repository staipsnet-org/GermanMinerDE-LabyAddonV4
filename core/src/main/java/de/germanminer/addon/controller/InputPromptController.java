package de.germanminer.addon.controller;

import net.labymod.api.reference.annotation.Referenceable;
import org.jetbrains.annotations.Nullable;

@Nullable
@Referenceable
public abstract class InputPromptController {

  public abstract void openInputPrompt(String message, String value, String submitText, String cancelText);

}