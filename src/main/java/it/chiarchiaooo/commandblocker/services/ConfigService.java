package it.chiarchiaooo.commandblocker.services;

import it.chiarchiaooo.commandblocker.CommandBlocker;

import java.util.Objects;
import java.util.Set;

public class ConfigService {
    
    private final CommandBlocker main;
    private final MsgService msgService;
    private final VarService varService;



    public ConfigService(CommandBlocker main) {
        this.main = main;
        this.msgService = main.getMsgService();
        this.varService = main.getVarService();
        
        makeConfigs();
        setMessages();
        setLists();

        main.getLogger().info("Config caricati");
    }

    public void makeConfigs() {
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();
    }


    public void setMessages() {
        varService.setPrefix(msgService.formatMsg(main.getConfig().getString("prefix")));
        varService.setNoArgsErrorMsg(msgService.formatMsg(main.getConfig().getString("cmd-arg-not-found-message")));
        varService.setCmdBlockedMsg(msgService.formatMsg(main.getConfig().getString("blocked-command-message")));
    }

    public void setLists() {
        varService.setSingleCmdWhitelist(main.getConfig().getStringList("single-allowed-cmds"));
        varService.setCmdWhitelist(main.getConfig().getStringList("allowed-cmds"));

        for (String group : getCustomConfigurations("groups")) {
            varService.getCmdGroupCommands().put(group, main.getConfig().getStringList("groups." + group + ".commands"));
        }

    }

    public Set<String> getCustomConfigurations(String path) {return Objects.requireNonNull(main.getConfig()
            .getConfigurationSection(path)).getKeys(false);}

}

