package it.chiarchiaooo.commandblocker;

import it.chiarchiaooo.commandblocker.commands.MainCommand;
import it.chiarchiaooo.commandblocker.listeners.CommandListener;
import it.chiarchiaooo.commandblocker.listeners.TabSuggestListener;
import it.chiarchiaooo.commandblocker.services.ConfigService;
import it.chiarchiaooo.commandblocker.services.MsgService;
import it.chiarchiaooo.commandblocker.services.VarService;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class CommandBlocker extends JavaPlugin {

    @Getter
    private static CommandBlocker instance;
    private ConfigService configService;
    private VarService varService;
    private MsgService msgService;


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
        getLogger().info("§aPlugin successfully enabled");
        getLogger().info("§6Remember to rate this plugin on spigotmc.org");
    }

    private void setupServices() {
        varService = new VarService(this);
        msgService = new MsgService(this);
        configService = new ConfigService(this);
    }


    public void setupCommands() {
        getCommand("cmdblock").setExecutor(new MainCommand(this));
        getCommand("cmdblock").setTabCompleter(new TabSuggestListener(this));

        getLogger().info("Comandi registrati");
    }

    public void setupEvents() {
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);

        getLogger().info(
                "Tab blocking? " + varService.isTabBlockingEnabled() +
                        "Perm commands? " + varService.isPermBasedCommandEnabled() +
                        "Group cmds" + varService.isCommandGroupsEnabled()
        );

        if (varService.isTabBlockingEnabled()) // Makes sure tab blocker is enabled from config
            getServer().getPluginManager().registerEvents(new TabSuggestListener(this), this);

        getLogger().info("Eventi registrati");

    }

    public void onDisable() {
        getLogger().info("§cPlugin successufully Disabled");
    }
}

//  - /cmdblock info: Shows plugin infos
//  - /cmdblock reload: Reload config file