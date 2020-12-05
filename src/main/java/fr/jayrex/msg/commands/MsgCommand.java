package fr.jayrex.msg.commands;

import fr.jayrex.msg.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MsgCommand extends Command {
  public MsgCommand() {
    super("msg");
  }
  
  @SuppressWarnings("deprecation")
public void execute(CommandSender sender, String[] args) {
    if (args.length == 0)
      for (String s : Main.get().getConfig().getStringList("MsgHelp"))
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET)));  
    if (args.length > 0)
      if (sender instanceof ProxiedPlayer) {
        ProxiedPlayer player = (ProxiedPlayer)sender;
        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
        if (target == null) {
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("PlayerOffline").replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET).replace("%player", player.getName()).replace("%target", args[0])));
          return;
        } 
        if (args.length > 1) {
          StringBuilder sb = new StringBuilder("");
          for (int i = 1; i < args.length; i++)
            sb.append(args[i]).append(" "); 
          String msg = sb.toString();
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("For").replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET).replace("%player", player.getName()).replace("%target", target.getName()).replace("%msg", msg)));
          target.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("From").replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET).replace("%player", player.getName()).replace("%target", target.getName()).replace("%msg", msg)));
          if (!Main.r.containsKey(player)) {
            Main.r.put(player, target);
          } else {
            Main.r.replace(player, target);
          } 
          if (!Main.r.containsKey(target)) {
            Main.r.put(target, player);
          } else {
            Main.r.replace(target, player);
          } 
        } else {
          sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("NoMessage").replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET)));
        } 
      }  
  }
}
