package it.chiarchiaooo.commandblocker;

import java.util.logging.Logger;

import it.chiarchiaooo.commandblocker.Commands.ReloadCommand;
import it.chiarchiaooo.commandblocker.Listeners.CommandEvent;
import it.chiarchiaooo.commandblocker.Listeners.TabCompleteEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;

    private static Logger log = Bukkit.getLogger();

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        startPlugin();
    }

    private void startPlugin() {
        log.info("");
        log.info(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker"));
        log.info("");
        log.info(ChatColor.translateAlternateColorCodes('&',"&aEnabled "));
        log.info(ChatColor.translateAlternateColorCodes('&',"&eMade by &6Chiarchiaooo&7 (&6Sussoliny#9971&7)"));
        registerCommands();
        registerEvents();
        saveDefaultConfig();
    }

    public void restartPlugin() {
        onDisable();
        onEnable();
        log.info(" ");
        log.info(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker"));
        log.info(" ");
        log.info(ChatColor.translateAlternateColorCodes('&',"&aForced restart of the plugin."));
        log.info(ChatColor.WHITE + "&aThis event could affect recent plugin config changes");
    }

    public void reloadPlugin() {
        reloadConfig();
        log.info(" ");
        log.info(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker"));
        log.info(" ");
        log.warning(ChatColor.translateAlternateColorCodes('&',"&8[&c&lWarn&8] &fA player ran the plugin's reload command."));
        log.info(ChatColor.GREEN + "Reloaded Succesfully!");
    }

    private void registerCommands() {
        getCommand("cmdblockreload").setExecutor(new ReloadCommand());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        getServer().getPluginManager().registerEvents(new TabCompleteEvent(), this);
    }

    public void onDisable() {
        saveDefaultConfig();
        getLogger().info(ChatColor.RED + "Disabled ");
    }
}