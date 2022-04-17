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

    private FileConfiguration config;

    public CommandEvent(Main pl) {
        this.config = pl.getConfig();
    }

     List<String> cmds = config.getStringList("allowed-cmds");
     List<String> staffcmds = config.getStringList("single-allowed-cmds");
     String blockmsg = config.getString("blocked-command-message");
     boolean BlockEnable = config.getBoolean("enabled");
     String prefix = config.getString("prefix");
     Map<String, String> commandBypasses = new HashMap<>();
     final String genbypass = "cmdblock.bypass.*";
     boolean block = true;

    @EventHandler
    public void CmdEvent(PlayerCommandPreprocessEvent event){
        if (BlockEnable) {
            if (event.getPlayer().hasPermission(genbypass)) {
                return;
            }
            String msg = blockmsg.replace("%prefix%",prefix).replace("%player%",event.getPlayer().getDisplayName()).replace("%command%",event.getMessage());
            for (String staffalcmds : staffcmds) {
                commandBypasses.put(staffalcmds, "cmdblock.bypass." + staffalcmds.substring(1));
            }
            for (String alcmd : cmds) {
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
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',msg));
            }
        }
    }
}