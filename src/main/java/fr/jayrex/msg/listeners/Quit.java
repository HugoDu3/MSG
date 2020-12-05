package fr.jayrex.msg.listeners;

import fr.jayrex.msg.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Quit implements Listener {
  @EventHandler
  public void onQuit(PlayerDisconnectEvent e) {
    ProxiedPlayer p = e.getPlayer();
    if (Main.r.containsKey(p)) {
      ProxiedPlayer target = Main.r.get(p);
      if (target != null)
        if (Main.r.get(target) == p)
          Main.r.remove(target);  
      Main.r.remove(p);
    } 
  }
}
