package it.chiarchiaooo.commandblocker.Listeners;

import it.chiarchiaooo.commandblocker.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandEvent implements Listener {

    private final FileConfiguration config;
    Map<String, String> commandBypasses = new HashMap<>();
    final String genbypass = "cmdblock.bypass.*";
    boolean block = true;

    public CommandEvent(Main pl) {
        this.config = pl.getConfig();
    }
    

    @EventHandler
    public void CmdEvent(PlayerCommandPreprocessEvent event){
        if (config.getBoolean("enabled")) {
            if (event.getPlayer().hasPermission(genbypass)) {
                return;
            }
            for (String staffalcmds : config.getStringList("single-allowed-cmds")) {
                commandBypasses.put(staffalcmds, "cmdblock.bypass." + staffalcmds.substring(1));
            }
            for (String alcmd : config.getStringList("allowed-cmds")) {
                if (StringUtils.startsWithIgnoreCase(event.getMessage(), alcmd)) {
                    block = false;
                    break;
                }
            }
            for (Map.Entry m : commandBypasses.entrySet()) {
                if (StringUtils.startsWithIgnoreCase(event.getMessage(), (String) m.getKey())) {
                    if (event.getPlayer().hasPermission((String) m.getValue())) {
                        block = false;
                        break;                    }
                }
            }
            if (block) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("blocked-command-message").replace("%prefix%",config.getString("prefix")).replace("%player%",event.getPlayer().getDisplayName()).replace("%command%",event.getMessage())));
            }
        }
    }
}