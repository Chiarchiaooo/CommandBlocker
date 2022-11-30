package it.chiarchiaooo.commandblocker.services;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;
import java.util.Set;

public class ConfigService {

    private final CommandBlocker main;
    private final MsgService msgService;
    private final VarService varService;

    private final FileConfiguration config;


    public ConfigService(CommandBlocker main) {
        this.main = main;
        this.msgService = main.getMsgService();
        this.varService = main.getVarService();
        this.config = main.getConfig();

        makeConfigs();
        setMessages();
        setBools();
        setLists();

        main.getLogger().info("Config caricati");
    }


    public void makeConfigs() {
        config.options().copyDefaults();
        main.saveDefaultConfig();
    }


    public void setMessages() {
        varService.setPrefix(msgService.formatMsg(config.getString("prefix")));
        varService.setNoArgsErrorMsg(msgService.formatMsg(config.getString("cmd-arg-not-found-message")));
        varService.setCmdBlockedMsg(msgService.formatMsg(config.getString("blocked-command-message")));
    }

    public void setBools() {
        varService.setTabBlockingEnabled(config.getBoolean("toggles.tab-blocker"));
        varService.setCommandGroupsEnabled(config.getBoolean("toggles.command-groups"));
        varService.setPermBasedCommandEnabled(config.getBoolean("toggles.perm-based-commands"));
    }

    public void setLists() {
        varService.setSingleCmdWhitelist(config.getStringList("single-allowed-cmds"));
        varService.setCmdWhitelist(config.getStringList("allowed-cmds"));

        for (String group : getCustomConfigurations("groups"))
            varService.getCmdGroupCommands().put(group, config.getStringList("groups." + group + ".commands"));


    }

    public Set<String> getCustomConfigurations(String path) {
        return Objects.requireNonNull(main.getConfig()
                .getConfigurationSection(path)).getKeys(false);
    }

}

