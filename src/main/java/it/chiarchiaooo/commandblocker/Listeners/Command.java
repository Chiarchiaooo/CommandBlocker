package it.chiarchiaooo.commandblocker.Listeners;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.utils.Messages;
import it.chiarchiaooo.commandblocker.utils.Vars;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Command implements Listener {

    
    
    private final Vars vars = CommandBlocker.getVars();
    private final Messages msgHandler = CommandBlocker.getMsgHandler();
    private final FileConfiguration config = CommandBlocker.getInstance().getConfig();
    

    @EventHandler
    public void CmdEvent(PlayerCommandPreprocessEvent e){
        Player p = e.getPlayer();

        String[] msg = e.getMessage().split(" ");
        String cmd = msg[0];
        String arg = msg[1];

        if (arg.equalsIgnoreCase("help") || p.hasPermission(vars.getGENERAL_CMD_BYPASS()) || vars.getCmdWhitelist().contains(cmd)) return;

        else if (hasPermission(p,cmd)) return;

        else if (vars.getAreCommandGroupsEnabled() && isGroupCmd(p,cmd)) return; // first check: are command groups enabled?, second check: is the player in a group? if yes, is the command in the group cmd list?

        e.setCancelled(true);
        p.sendMessage(msgHandler.sendMsg(p, vars.getCmdBlockedMsg()).replaceAll("%command%|%cmd%", cmd));
    }


    /**
     *
     * Checks to see if the player has the permission to execute that command
     *
     * @param p The player to check
     * @param cmd The cmd the player is trying to execute
     *
     * @return Whether the Cmd is blocked or not
     */

    private boolean hasPermission(Player p, String cmd) {return p.hasPermission("cmdblock.bypass."+cmd);}

    /**
     *
     * Get the player command group by checking if he has the group permissison
     *
     * @param p The player to check
     *
     * @return The command group
     */

    private String getPlayerCmdGroup(Player p) {
        List<String> keys = vars.getCmdGroups();
        for (String group : keys) {

            if (p.hasPermission(Objects.requireNonNull(config.getString("groups." + group + ".permission")))) return group;
        }
        return "";
    }

    /**
     *
     * Checks to see if a cmd is blocked using permission groups and regex patterns
     *
     * @param p The player to check
     * @param cmd The cmd the player is trying to execute
     *
     * @return Whether the Cmd is blocked or not
     */

    private boolean isGroupCmd(Player p, String cmd) {return !getPlayerCmdGroup(p).isEmpty() && Pattern.compile(cmdsToPattern(getPlayerCmdGroup(p))).matcher(cmd).find();}

    /**
     *
     * Converts a list of cmd into a String regex pattern
     *
     * @param group The group associated with the list of cmd to be converted
     *
     * @return The list converted into a string regex pattern
     */

    private String cmdsToPattern(String group) { return String.join("|", config.getStringList("groups." + group + ".commands")); }
}