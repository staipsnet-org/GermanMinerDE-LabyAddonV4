package de.germanminer.addon.controller;

import net.labymod.api.reference.annotation.Referenceable;
import org.jetbrains.annotations.Nullable;

@Deprecated(since = "3.8.0")
@Nullable
@Referenceable
public abstract class GermanMinerController {

  public abstract void sendPayload(final String channel, final byte[] payload);

}
