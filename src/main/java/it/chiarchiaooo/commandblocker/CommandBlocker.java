package it.chiarchiaooo.commandblocker;


import it.chiarchiaooo.commandblocker.Listeners.Command;
import it.chiarchiaooo.commandblocker.Listeners.TabComplete;
import it.chiarchiaooo.commandblocker.Managers.Commands;
import it.chiarchiaooo.commandblocker.Managers.Config;
import it.chiarchiaooo.commandblocker.Managers.TabCompleter;
import it.chiarchiaooo.commandblocker.utils.Messages;
import it.chiarchiaooo.commandblocker.utils.Vars;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandBlocker extends JavaPlugin {

    @Getter static CommandBlocker instance;

    @Getter static Vars vars = new Vars();

    @Getter static Messages msgHandler = new Messages();

    @Setter Config config;


    public void onEnable() {
        getLogger().info("");
        getLogger().info("§6§lCommandBlocker");
        getLogger().info("");
        getLogger().info("§eMade by §6Chiarchiaooo§7 (§6~Luke#0002§7)");
        getLogger().info("");

        setConfig(new Config());
        setupEvents();
        setupCommands();

        getLogger().info("");
        getLogger().info(ChatColor.GREEN+"Plugin successfully enabled");
        Bukkit.getConsoleSender().sendMessage("[CommandBlocker] "+ChatColor.GOLD+"Remember to rate this plugin on spigotmc.org");
        getLogger().info("");
    }

    @SuppressWarnings("all")
    public void setupCommands() {
        Commands handler = new Commands(); //register cmds
        getCommand("cmdblock").setExecutor(handler);
        getCommand("cmdblock").setTabCompleter(new TabCompleter());

        getLogger().info("Comandi registrati");
    }

    public void setupEvents() {
        getServer().getPluginManager().registerEvents(new Command(), this);
        if (vars.getIsTabBlockingEnabled()) getServer().getPluginManager().registerEvents(new TabComplete(), this);  // Make sure tab blocker is enabled from

        getLogger().info("Comandi registrati");// config
    }

    public void onDisable() {
        getLogger().info(ChatColor.RED + "Disabled ");
    }
}

//  - /cmdblock info: Shows plugin infos
//  - /cmdblock reload: Reload config file
//  - /cmdblock restart: Plugin force restart