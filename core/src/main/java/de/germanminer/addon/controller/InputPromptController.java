package de.germanminer.addon.controller;

import net.labymod.api.reference.annotation.Referenceable;
import org.jetbrains.annotations.Nullable;

@Nullable
@Referenceable
public abstract class InputPromptController {

  public abstract void openInputPrompt(final String message, final String value, final String textSubmit, final String textCancel);

}
