package it.chiarchiaooo.commandblocker.listeners;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.services.MsgService;
import it.chiarchiaooo.commandblocker.services.VarService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandListener implements Listener {

    private CommandBlocker main;
    private final VarService varService;
    private final MsgService msgService;

    public CommandListener(CommandBlocker main) {
        this.main = main;
        this.varService = main.getVarService();
        this.msgService = main.getMsgService();

    }


    @EventHandler (ignoreCancelled = true)
    public void CmdEvent(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();

        String[] msg = e.getMessage().split(" ");
        String cmd = msg[0];
        String arg = msg[1];

        if (arg.equalsIgnoreCase("help") || p.hasPermission(varService.getCmdBypassPermission())
                || varService.getCmdWhitelist().contains(cmd))
            return;

        else if (hasCmdPermission(p, cmd) || (varService.isCommandGroupsEnabled() && isGroupCmd(p, cmd))) return;


        e.setCancelled(true);
        p.sendMessage(msgService.setPlaceholders(p, varService.getCmdBlockedMsg())
                            .replaceAll("%command%|%cmd%", cmd)
        );
    }


    /**
     * Checks to see if the player has the permission to execute that command
     *
     * @param p   The player to check
     * @param cmd The cmd the player is trying to execute
     * @return Whether the Cmd is blocked or not
     */

    private boolean hasCmdPermission(Player p, String cmd) {
        return p.hasPermission("cmdblock.bypass." + cmd);
    }

    /**
     * Get the player command group by checking if he has the group permissison
     *
     * @param p The player to check
     * @return The command group
     */

    private List<String> getGroupCmds(Player p) {
        for (Map.Entry<String,List<String>> m : varService.getCmdGroupCommands().entrySet()) {

            if (p.hasPermission(m.getKey())) return m.getValue();

        }
        return new ArrayList<>();
    }

    /**
     * Checks to see if a cmd is blocked using permission groups and regex patterns
     *
     * @param p The player trying to execute the command
     * @param cmd The cmd the player is trying to execute
     * @return Whether the Cmd is blocked or not
     */

    private boolean isGroupCmd(Player p, String cmd) {
        if (getGroupCmds(p).isEmpty()) return false;

        Pattern regexPattern = Pattern.compile( listToPattern(getGroupCmds(p)) );
        Matcher regexMatcher = regexPattern.matcher(cmd);

        return regexMatcher.find();
    }

    /**
     * Converts a list of cmds into a String regex pattern
     *
     * @param groupCmds The commands list to convert
     * @return The list converted into a string regex pattern
     */

    private String listToPattern(List<String> groupCmds) {return String.join("|", groupCmds);}
}