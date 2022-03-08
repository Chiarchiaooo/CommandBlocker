package it.chiarchiaooo.commandblocker;

import java.util.logging.Logger;

import it.chiarchiaooo.commandblocker.Commands.Arguments.HelpArgument;
import it.chiarchiaooo.commandblocker.Commands.Arguments.RestartArgument;
import it.chiarchiaooo.commandblocker.Commands.MainCommand;
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
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        log.info("");
        log.info(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker"));
        log.info("");
        log.info(ChatColor.translateAlternateColorCodes('&',"&aEnabled "));
        log.info(ChatColor.translateAlternateColorCodes('&',"&eMade by &6Chiarchiaooo&7 (&6Sussoliny#9971&7)"));
        CommandHandler handler = new CommandHandler();
        handler.register("cmdblock",new MainCommand());
        handler.register("restart",new RestartArgument());
        handler.register("help",new HelpArgument());
        getCommand("cmdblock").setExecutor(handler);
        getCommand("cmdblock").setTabCompleter(new TabCompleteHandler());

        getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        getServer().getPluginManager().registerEvents(new TabCompleteEvent(), this);
    }

    public void onDisable() {
        saveDefaultConfig();
        getLogger().info(ChatColor.RED + "Disabled ");
    }
}

//  - /cmdblock info: Shows plugin infos
//  - /cmdblock reload: Reload config file
//  - /cmdblock restart: Plugin force restart