package it.chiarchiaooo.commandblocker;

import it.chiarchiaooo.commandblocker.commands.MainCommand;
import it.chiarchiaooo.commandblocker.listeners.CommandListener;
import it.chiarchiaooo.commandblocker.listeners.TabSuggestListener;
import it.chiarchiaooo.commandblocker.services.ConfigService;
import it.chiarchiaooo.commandblocker.services.MsgService;
import it.chiarchiaooo.commandblocker.services.VarService;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class CommandBlocker extends JavaPlugin {

    private final ConsoleCommandSender CONSOLE = Bukkit.getConsoleSender();

    @Getter
    private static CommandBlocker instance;

    private ConfigService configService;
    private VarService varService;
    private MsgService msgService;


    public void onEnable() {
        long timestampStart = System.currentTimeMillis();
        instance = this;


        CONSOLE.sendMessage("");
        CONSOLE.sendMessage("§6§lCommandBlocker");
        CONSOLE.sendMessage("");
        CONSOLE.sendMessage("§eMade by §6Chiarchiaooo§7 (§6~Luke#7123§7)");
        CONSOLE.sendMessage("");

        setupServices();
        setupEvents();
        setupCommands();

        int processingTime = (int) (System.currentTimeMillis() - timestampStart);
        CONSOLE.sendMessage("");
        CONSOLE.sendMessage("§aPlugin successfully enabled (in " + processingTime + " ms)");
        CONSOLE.sendMessage("§6Remember to rate this plugin on spigotmc.org");
    }

    private void setupServices() {
        varService = new VarService(this);
        msgService = new MsgService(this);
        configService = new ConfigService(this);

        CONSOLE.sendMessage("Services loaded");
    }


    public void setupCommands() {
        getCommand("cmdblock").setExecutor(new MainCommand(this));
        getCommand("cmdblock").setTabCompleter(new TabSuggestListener(this));

        CONSOLE.sendMessage("Commands registered");
    }

    public void setupEvents() {
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);

        if (varService.isTabBlockingEnabled()) // Makes sure tab blocker is enabled from config
            getServer().getPluginManager().registerEvents(new TabSuggestListener(this), this);

        CONSOLE.sendMessage("Events registered");

    }

    public void onDisable() {
        CONSOLE.sendMessage("§cPlugin successfully Disabled");
    }
}
