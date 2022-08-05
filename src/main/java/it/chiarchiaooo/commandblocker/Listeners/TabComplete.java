package it.chiarchiaooo.commandblocker.Listeners;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.utils.Vars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;
import java.util.Map;

public class TabComplete implements Listener {

    private final Vars vars = CommandBlocker.getVars();



    @EventHandler(ignoreCancelled = true)
    public void onCommandSend(final PlayerCommandSendEvent event) { //commands suggestions check on player join (rejoin after plugin reload to apply changes)
        Player p = event.getPlayer();

        if (p.hasPermission(vars.getGENERAL_CMD_BYPASS())) return;

        event.getCommands().clear(); // Remove every command suggestion

        event.getCommands().addAll(vars.getCmdWhitelist()); //add all the commands from the general allowed commands list

        if (vars.getAreSingleCommandEnabled()) setupSingleCmds(event,p); // add all permission-based command to suggestion list

        if (vars.getAreCommandGroupsEnabled()) setGroupCmds(event,p); // add all the player's group commands to the suggestion list



    }

    /**
     *
     * Add all the player's command group commands to player's suggestion list
     *
     * @param e The event
     * @param p The player to check
     */

    private void setGroupCmds(PlayerCommandSendEvent e, Player p) {
        for (Map.Entry<String, List<String>> groups : vars.getCmdGroupCommands().entrySet()) {

            if (p.hasPermission(groups.getKey())) e.getCommands().addAll(groups.getValue());

        }
    }

    /**
     *
     * Add all commands the player has access to his suggestion list
     *
     * @param e The event
     * @param p The player to check
     */

    private void setupSingleCmds(PlayerCommandSendEvent e, Player p) {
        for (String command : vars.getSingleCmdWhitelist()) {

            if (p.hasPermission("cmdblock.bypass."+command)) e.getCommands().add(command);

        }
    }
}