package de.germanminer.addon.listener;

import net.labymod.api.event.Event;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.NetworkPayloadEvent;

public class PacketListener {

  @Subscribe
  public void onTest(NetworkPayloadEvent event) {
    System.out.println(event.identifier());
    System.out.println(event.getPayload().toString());
  }

}
