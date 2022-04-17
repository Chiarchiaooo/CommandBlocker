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

    private FileConfiguration config;

    public TabCompleteEvent(Main pl) {
        this.config = pl.getConfig();
    }

    //config and class variables declaration
    boolean BlockEnable = config.getBoolean("enabled");
    List<String> cmds = config.getStringList("allowed-cmds");
    List<String> staffcmds = config.getStringList("single-allowed-cmds");
    Map<String, String> commandBypasses = new HashMap<>();
    final String genbypass = "cmdblock.bypass.*";

    @EventHandler(ignoreCancelled = true)
    public void onCommandSend(final PlayerCommandSendEvent event) { //commands suggestions check on player join (rejoin after plugin reload to apply changes)
        // Make sure command blocker is enabled from config
        if (BlockEnable) {
            if (event.getPlayer().hasPermission(genbypass)) {return;} //check for global bypass permission: orientalcmds.cmd.bypass.*

            event.getCommands().clear(); // Remove every command suggestion

            for (String staffalcmds : staffcmds) {  //creating hashmap for permission blocked commands
                commandBypasses.put(staffalcmds, "cmdblock.bypass." + staffalcmds.substring(1)); //layout: command (with /) , orientalcmds.command.bypass.<command> (without /)
            }

            for (String str: cmds) { //loop between normal allowed commands (commands that everybody can execute and see)
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