package it.chiarchiaooo.commandblocker;


import it.chiarchiaooo.commandblocker.Commands.Arguments.HelpArgument;
import it.chiarchiaooo.commandblocker.Commands.Arguments.RestartArgument;
import it.chiarchiaooo.commandblocker.Commands.MainCommand;
import it.chiarchiaooo.commandblocker.Listeners.CommandEvent;
import it.chiarchiaooo.commandblocker.Listeners.TabCompleteEvent;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;


    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
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

        CommandHandler handler = new CommandHandler(); //register cmds
        handler.register("cmdblock",new MainCommand());
        handler.register("restart",new RestartArgument());
        handler.register("help",new HelpArgument());
        getCommand("cmdblock").setExecutor(handler);
        getCommand("cmdblock").setTabCompleter(new TabCompleteHandler());

        getServer().getPluginManager().registerEvents(new CommandEvent(), this); //register events
        getServer().getPluginManager().registerEvents(new TabCompleteEvent(), this);
    }

    public void onDisable() {
        getLogger().info(ChatColor.RED + "Disabled ");
    }
}

//  - /cmdblock info: Shows plugin infos
//  - /cmdblock reload: Reload config file
//  - /cmdblock restart: Plugin force restart