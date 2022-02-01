package it.chiarchiaooo.commandblocker;

import java.util.logging.Logger;

import it.chiarchiaooo.commandblocker.Commands.ReloadCommand;
import it.chiarchiaooo.commandblocker.Listeners.CommandEvent;
import it.chiarchiaooo.commandblocker.Listeners.TabCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;

    private static Logger log = Bukkit.getLogger();

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        log.info("");
        log.info(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker"));
        log.info("");
        log.info(ChatColor.translateAlternateColorCodes('&',"&aEnabled "));
        log.info(ChatColor.translateAlternateColorCodes('&',"&eMade by &6Chiarchiaooo&7 (&6Sussoliny#9971&7)"));
        saveDefaultConfig();
        getCommand("cmdblock").setExecutor(new ReloadCommand());

        getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        getServer().getPluginManager().registerEvents(new TabCompleteEvent(), this);
    }

    public void onDisable() {
        saveDefaultConfig();
        getLogger().info(ChatColor.RED + "Disabled ");
    }
}