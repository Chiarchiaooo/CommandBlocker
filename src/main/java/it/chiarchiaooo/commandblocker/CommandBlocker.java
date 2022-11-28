package it.chiarchiaooo.commandblocker;

import it.chiarchiaooo.commandblocker.listeners.CommandListener;
import it.chiarchiaooo.commandblocker.listeners.TabSuggestListener;
import it.chiarchiaooo.commandblocker.commands.MainCommand;
import it.chiarchiaooo.commandblocker.services.ConfigService;
import it.chiarchiaooo.commandblocker.services.MsgService;
import it.chiarchiaooo.commandblocker.services.VarService;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class CommandBlocker extends JavaPlugin {

    @Getter
    private static CommandBlocker instance;
    private VarService varService;
    private MsgService msgService;
    private ConfigService configService;


    public void onEnable() {
        instance = this;
        getLogger().info("");
        getLogger().info("§6§lCommandBlocker");
        getLogger().info("");
        getLogger().info("§eMade by §6Chiarchiaooo§7 (§6~Luke#0002§7)");
        getLogger().info("");

        setupServices();
        setupEvents();
        setupCommands();

        getLogger().info("");
        getLogger().info(ChatColor.GREEN+"Plugin successfully enabled");
        Bukkit.getConsoleSender().sendMessage("[CommandBlocker] "+ChatColor.GOLD+"Remember to rate this plugin on spigotmc.org");
        getLogger().info("");
    }

    private void setupServices() {
        varService = new VarService(this);
        msgService = new MsgService(this);
        configService = new ConfigService(this);
    }

    @SuppressWarnings("all")
    public void setupCommands() {
        getCommand("cmdblock").setExecutor(new MainCommand(this));
        getCommand("cmdblock").setTabCompleter(new TabSuggestListener(this));

        getLogger().info("Comandi registrati");
    }

    public void setupEvents() {
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        if (varService.isTabBlockingEnabled()) getServer().getPluginManager()
                .registerEvents(new TabSuggestListener(this), this);  // Make sure tab blocker is enabled from

        getLogger().info("Comandi registrati");// config
    }

    public void onDisable() {
        getLogger().info(ChatColor.RED + "Disabled ");
    }
}

//  - /cmdblock info: Shows plugin infos
//  - /cmdblock reload: Reload config file
//  - /cmdblock restart: Plugin force restart