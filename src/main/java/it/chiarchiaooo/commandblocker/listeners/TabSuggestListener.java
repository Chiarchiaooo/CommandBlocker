package it.chiarchiaooo.commandblocker.listeners;

import it.chiarchiaooo.commandblocker.services.VarService;
import org.bukkit.event.player.PlayerCommandSendEvent;
import it.chiarchiaooo.commandblocker.CommandBlocker;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.EventHandler;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TabSuggestListener implements Listener, TabCompleter {

    private final VarService varService;
    private final CommandBlocker main;

    public TabSuggestListener(CommandBlocker main) {
        this.main = main;
        this.varService = main.getVarService();
    }

    // Tab completer for the /cmdblock command
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length == 1) return Arrays.asList("help", "reload", "reset");
        return new ArrayList<>();
    }

    /**
     * Commands suggestions check on player join (rejoin after plugin reload to apply changes)
     *
     * @param event Event fired when an MC client asks the server for the list of available commands after joining the server
     */

    @EventHandler(ignoreCancelled = true)
    public void onCommandSend(final PlayerCommandSendEvent event) {
        Player p = event.getPlayer();

        main.getLogger().info("Bypass tab blocking? " + p.hasPermission(varService.getCmdBypassPermission()));
        if (p.hasPermission(varService.getCmdBypassPermission())) return;

        // Removes every command suggestion
        event.getCommands().clear();


        // New command suggestion list based on the general whitelist
        List<String> allowedCommands = varService.getCmdWhitelist();

        // Adds all permission-based command to the new suggestion list
        if (varService.isPermBasedCommandEnabled())
            allowedCommands.addAll(setupSingleCmds(p));

        // Adds all the player's group commands to the new suggestion list
        if (varService.isCommandGroupsEnabled())
            allowedCommands.addAll(setGroupCmds(p));

        // Adds back the ew
        event.getCommands().addAll(allowedCommands);

        main.getLogger().info("Comandi: " + event.getCommands());
    }

    /**
     * Adds all the player's command group commands to player's suggestion list
     *
     * @param p The player to check
     */

    private List<String> setGroupCmds(Player p) {
        List<String> l = new ArrayList<>();

        for (Map.Entry<String, List<String>> groups : varService.getCmdGroupCommands().entrySet()) {

            if (p.hasPermission(groups.getKey())) l.addAll(groups.getValue());

        }

        return l;
    }

    /**
     * Adds all commands the player has access to his suggestion list
     *
     * @param p The player to check
     */

    private List<String> setupSingleCmds(Player p) {
        List<String> l = new ArrayList<>();

        for (String command : varService.getSingleCmdWhitelist()) {

            if (p.hasPermission("cmdblock.bypass." + command)) l.add(command);

        }

        return l;
    }
}