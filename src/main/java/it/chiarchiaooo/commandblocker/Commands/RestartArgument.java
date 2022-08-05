package it.chiarchiaooo.commandblocker.Commands;

import it.chiarchiaooo.commandblocker.Managers.Commands;
import it.chiarchiaooo.commandblocker.CommandBlocker;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RestartArgument implements Commands.MainCmd {

    private final CommandBlocker plugin = CommandBlocker.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("cmdblock.restart")) {
            sender.sendMessage(" ");
            plugin.getLogger().info(ChatColor.GREEN+"Plugin restarted");
            plugin.onDisable();
            plugin.onEnable();
        }
        return false;
    }
}