package it.chiarchiaooo.commandblocker.Listeners;

import it.chiarchiaooo.commandblocker.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabCompleteEvent implements Listener {

    //variables declaration
    private final FileConfiguration config;
    private final Map<String, String> commandBypasses;

    public TabCompleteEvent(Main pl) {
        this.config = pl.getConfig();
        this.commandBypasses = new HashMap<>();
    }


    @EventHandler(ignoreCancelled = true)
    public void onCommandSend(final PlayerCommandSendEvent event) { //commands suggestions check on player join (rejoin after plugin reload to apply changes)
        // Make sure command blocker is enabled from config
        if (config.getBoolean("enabled")) {
            if (event.getPlayer().hasPermission("cmdblock.bypass.*")) {return;} //check for global bypass permission: orientalcmds.cmd.bypass.*

            event.getCommands().clear(); // Remove every command suggestion

            for (String staffalcmds : config.getStringList("single-allowed-cmds")) {  //creating hashmap for permission blocked commands
                commandBypasses.put(staffalcmds, "cmdblock.bypass." + staffalcmds.substring(1)); //layout: command (with /) , orientalcmds.command.bypass.<command> (without /)
            }

            for (String str: config.getStringList("allowed-cmds")) { //loop between normal allowed commands (commands that everybody can execute and see)
                event.getCommands().add(str.substring(1)); // Remove blocked commands from the suggestions list.
            }

            for (Map.Entry m : commandBypasses.entrySet()) { //loop  between permission blocked commands (only people with permission orientalcmds.command.bypass.<command>(without /) )
                if (event.getPlayer().hasPermission((String) m.getValue())) { // m.getKey() = command , m.getValue() = permission
                    String str = (String) m.getKey();
                    event.getCommands().add(str.substring(1)); // Readded permission blocked commands
                }
            }
        }
    }
}