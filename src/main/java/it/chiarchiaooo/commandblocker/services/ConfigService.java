package it.chiarchiaooo.commandblocker.services;

import it.chiarchiaooo.commandblocker.CommandBlocker;

import java.util.ArrayList;
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

    private void makeConfigs() {
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();
    }


    private void setMessages() {
        varService.setPrefix(msgService.sendMsg(main.getConfig().getString("prefix")));
        varService.setNoArgsErrorMsg(msgService.sendMsg(main.getConfig().getString("cmd-arg-not-found-message")));
        varService.setCmdBlockedMsg(msgService.sendMsg(main.getConfig().getString("blocked-command-message")));
    }

    private void setLists() {
        varService.setSingleCmdWhitelist(main.getConfig().getStringList("single-allowed-cmds"));
        varService.setCmdGroups(new ArrayList<>(getCustomConfigurations("groups")));
        varService.setCmdWhitelist(main.getConfig().getStringList("allowed-cmds"));

        for (String group : varService.getCmdGroups()) {
            varService.getCmdGroupCommands().put(group, main.getConfig().getStringList("groups." + group + ".commands"));
        }

    }

    public Set<String> getCustomConfigurations(String path) {return Objects.requireNonNull(main.getConfig()
            .getConfigurationSection(path)).getKeys(false);}

}

