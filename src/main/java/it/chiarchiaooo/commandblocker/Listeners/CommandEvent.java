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
    public static void CmdEvent(PlayerCommandPreprocessEvent event){
        String msg = event.getMessage();
        Player p = event.getPlayer();
        if (BlockEnable) {
            for (String staffalcmds : staffcmds) {
                commandBypasses.put(staffalcmds, "cmdblock.bypass." + staffalcmds.substring(1));
            }
            for (Map.Entry m : commandBypasses.entrySet()) {
                if (StringUtils.startsWithIgnoreCase(msg, (String) m.getKey())) {
                    if (p.hasPermission((String) m.getValue())) {
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
            if (p.hasPermission(genbypass)) {
                block = false;
            }
            if (block) {
                event.setCancelled(true);
                if (blockmsg == "default") {
                    p.sendMessage("Unknown command. Type \"/help\" for help.");
                    return;
                }
                blockmsg.replace("%prefix",prefix);
                blockmsg.replace("%player%",p.getDisplayName());
                blockmsg.replace("%command%",msg);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',blockmsg));
            }
        }
    }
}