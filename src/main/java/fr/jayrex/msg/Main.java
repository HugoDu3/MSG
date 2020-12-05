package fr.jayrex.msg;

import java.io.File;
import java.util.HashMap;

import fr.jayrex.msg.commands.MsgCommand;
import fr.jayrex.msg.commands.ReplyCommand;
import fr.jayrex.msg.listeners.Quit;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin {
  public static HashMap<ProxiedPlayer, ProxiedPlayer> r = new HashMap<>();
  
  public static Main instance;
  
  public Configuration config;
  
  public static Main get() {
    return instance;
  }
  
  public void onEnable() {
    instance = this;
    loadConfig();
    getProxy().getPluginManager().registerListener(this, new Quit());
    getProxy().getPluginManager().registerCommand(this, new MsgCommand());
    getProxy().getPluginManager().registerCommand(this, new ReplyCommand());
  }
  
  public Configuration getConfig() {
    return this.config;
  }
  
  public void loadConfig() {
    try {
      if (!getDataFolder().exists())
        getDataFolder().mkdir(); 
      File file = new File(getDataFolder(), "config.yml");
      if (!file.exists())
        ConfigurationProvider.getProvider(YamlConfiguration.class)
          .save(ConfigurationProvider.getProvider(YamlConfiguration.class).load(getResourceAsStream("config.yml")), 
            file); 
      this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
    } catch (Exception ex) {
      ex.printStackTrace();
    } 
  }  
}