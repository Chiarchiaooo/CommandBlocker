package it.chiarchiaooo.commandblocker.Managers;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.utils.Messages;
import it.chiarchiaooo.commandblocker.utils.Vars;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Config {

    private final Messages msgHandler = CommandBlocker.getMsgHandler();
    private final CommandBlocker pl = CommandBlocker.getInstance();
    private final FileConfiguration config = pl.getConfig();
    private final Vars vars = CommandBlocker.getVars();



    public Config() {
        makeConfigs();

        setMessages();
        setLists();

        pl.getLogger().info("Config caricati");
    }

    private void makeConfigs() {
        pl.getConfig().options().copyDefaults();
        pl.saveDefaultConfig();
    }


    private void setMessages() {
        vars.setPrefix(msgHandler.sendMsg(config.getString("prefix")));
        vars.setNoArgsErrorMsg(msgHandler.sendMsg(config.getString("cmd-arg-not-found-message")));
        vars.setCmdBlockedMsg(msgHandler.sendMsg(config.getString("blocked-command-message")));
    }

    private void setLists() {
        vars.setSingleCmdWhitelist(config.getStringList("single-allowed-cmds"));
        vars.setCmdGroups(new ArrayList<>(getCustomConfigurations("groups")));
        vars.setCmdWhitelist(config.getStringList("allowed-cmds"));

        for (String group : vars.getCmdGroups()) {
            vars.getCmdGroupCommands().put(group,config.getStringList("groups." + group + ".commands"));
        }

    }

    public Set<String> getCustomConfigurations(String path) {return Objects.requireNonNull(pl.getConfig().getConfigurationSection(path)).getKeys(false);}

}

