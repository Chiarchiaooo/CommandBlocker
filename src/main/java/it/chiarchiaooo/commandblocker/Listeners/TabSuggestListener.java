package it.chiarchiaooo.commandblocker.listeners;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.services.VarService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TabSuggestListener implements Listener, TabCompleter {

    private CommandBlocker main;
    private final VarService varService;

    public TabSuggestListener(CommandBlocker main) {
        this.main = main;
        this.varService = main.getVarService();
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        if (args.length == 1) return Arrays.asList("help", "reload");
        return new ArrayList<>();
    }

    @EventHandler(ignoreCancelled = true)
    public void onCommandSend(final PlayerCommandSendEvent event) { //commands suggestions check on player join (rejoin after plugin reload to apply changes)
        Player p = event.getPlayer();

        if (p.hasPermission(varService.getCmdBypassPermission())) return;

        event.getCommands().clear(); // Remove every command suggestion

        event.getCommands().addAll(varService.getCmdWhitelist()); //add all the commands from the general allowed commands list

        if (varService.isSingleCommandEnabled())
            setupSingleCmds(event, p); // add all permission-based command to suggestion list

        if (varService.isCommandGroupsEnabled())
            setGroupCmds(event, p); // add all the player's group commands to the suggestion list


    }

    /**
     * Add all the player's command group commands to player's suggestion list
     *
     * @param e The event
     * @param p The player to check
     */

    private void setGroupCmds(PlayerCommandSendEvent e, Player p) {
        for (Map.Entry<String, List<String>> groups : varService.getCmdGroupCommands().entrySet()) {

            if (p.hasPermission(groups.getKey())) e.getCommands().addAll(groups.getValue());

        }
    }

    /**
     * Add all commands the player has access to his suggestion list
     *
     * @param e The event
     * @param p The player to check
     */

    private void setupSingleCmds(PlayerCommandSendEvent e, Player p) {
        for (String command : varService.getSingleCmdWhitelist()) {

            if (p.hasPermission("cmdblock.bypass." + command)) e.getCommands().add(command);

        }
    }
}