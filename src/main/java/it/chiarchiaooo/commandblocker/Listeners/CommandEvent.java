package it.chiarchiaooo.commandblocker.Listeners;

import it.chiarchiaooo.commandblocker.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandEvent implements Listener {

    static List<String> cmds = Main.getInstance().getConfig().getStringList("allowed-cmds");
    static List<String> staffcmds = Main.getInstance().getConfig().getStringList("single-allowed-cmds");
    static String blockmsg = Main.getInstance().getConfig().getString("blocked-command-message");
    static boolean BlockEnable = Main.getInstance().getConfig().getBoolean("enabled");
    static String prefix = Main.getInstance().getConfig().getString("prefix");
    static Map<String, String> commandBypasses = new HashMap<>();
    static final String genbypass = "cmdblock.bypass.*";
    static boolean block = true;

    @EventHandler
    public static void CmdEvent(PlayerCommandPreprocessEvent event) {
        String msg = blockmsg.replace("%prefix%",prefix).replace("%player%",event.getPlayer().getDisplayName()).replace("%command%",event.getMessage());
        if (BlockEnable) {
            for (String staffalcmds : staffcmds) {
                commandBypasses.put(staffalcmds, "cmdblock.bypass." + staffalcmds.substring(1));
            }
            for (Map.Entry m : commandBypasses.entrySet()) {
                if (StringUtils.startsWithIgnoreCase(msg, (String) m.getKey())) {
                    if (event.getPlayer().hasPermission((String) m.getValue())) {
                        block = false;
                        break;
                    }
                }
            }
            for (String alcmd : cmds) {
                if (StringUtils.startsWithIgnoreCase(msg, alcmd)) {
                    block = false;
                    break;
                }
            }
            if (event.getPlayer().hasPermission(genbypass)) {
                block = false;
            }
            if (block) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',msg));
            }
        }
    }
}