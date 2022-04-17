package it.chiarchiaooo.commandblocker;


import it.chiarchiaooo.commandblocker.Commands.Arguments.HelpArgument;
import it.chiarchiaooo.commandblocker.Commands.Arguments.RestartArgument;
import it.chiarchiaooo.commandblocker.Commands.MainCommand;
import it.chiarchiaooo.commandblocker.Listeners.CommandEvent;
import it.chiarchiaooo.commandblocker.Listeners.TabCompleteEvent;
import it.chiarchiaooo.commandblocker.utils.CommandHandler;
import it.chiarchiaooo.commandblocker.utils.TabCompleteHandler;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    public HashMap<String, CommandHandler.CommandInterface> commands = new HashMap<>();

    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getLogger().info("");
        getLogger().info(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker"));
        getLogger().info("");
        getLogger().info(ChatColor.translateAlternateColorCodes('&',"&eMade by &6Chiarchiaooo&7 (&6Sussoliny#9971&7)"));
        getLogger().info("");
        getLogger().info("");
        getLogger().info(ChatColor.translateAlternateColorCodes('&',"&aPlugin successfully enabled "));
        getLogger().info("");

        CommandHandler handler = new CommandHandler(this); //register cmds
        handler.register("cmdblock",new MainCommand());
        handler.register("restart",new RestartArgument(this));
        handler.register("help",new HelpArgument());
        getCommand("cmdblock").setExecutor(handler);
        getCommand("cmdblock").setTabCompleter(new TabCompleteHandler(this));

        getServer().getPluginManager().registerEvents(new CommandEvent(this), this); //register events
        getServer().getPluginManager().registerEvents(new TabCompleteEvent(this), this);
    }

    public void onDisable() {
        getLogger().info(ChatColor.RED + "Disabled ");
    }
}

//  - /cmdblock info: Shows plugin infos
//  - /cmdblock reload: Reload config file
//  - /cmdblock restart: Plugin force restart