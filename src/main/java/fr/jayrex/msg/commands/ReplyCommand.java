package fr.jayrex.msg.commands;

import fr.jayrex.msg.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReplyCommand extends Command {
  public ReplyCommand() {
    super("r");
  }
  
  @SuppressWarnings("deprecation")
public void execute(CommandSender sender, String[] args) {
    if (args.length == 0)
      for (String s : Main.get().getConfig().getStringList("ReplyHelp"))
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s.replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET)));  
    if (args.length > 0)
      if (sender instanceof ProxiedPlayer) {
        ProxiedPlayer player = (ProxiedPlayer)sender;
        if (Main.r.containsKey(player)) {
          ProxiedPlayer target = Main.r.get(player);
          if (target == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("PlayerOffline").replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET).replace("%player", player.getName()).replace("%target", ((ProxiedPlayer)Main.r.get(player)).getName())));
            return;
          } 
          StringBuilder sb = new StringBuilder("");
          for (int i = 0; i < args.length; i++)
            sb.append(args[i]).append(" "); 
          String msg = sb.toString();
          player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("For").replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET).replace("%player", player.getName()).replace("%target", target.getName()).replace("%msg", msg)));
          target.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("From").replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET).replace("%player", player.getName()).replace("%target", target.getName()).replace("%msg", msg)));
        } else {
          sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("DontReply").replace("%prefix", String.valueOf(ChatColor.translateAlternateColorCodes('&', Main.get().getConfig().getString("Prefix"))) + ChatColor.RESET)));
        } 
      }  
  }
}
