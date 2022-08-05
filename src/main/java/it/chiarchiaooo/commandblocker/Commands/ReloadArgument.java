package it.chiarchiaooo.commandblocker.Commands;

import it.chiarchiaooo.commandblocker.Managers.Commands;
import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.Managers.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ReloadArgument implements Commands.MainCmd {


    private final CommandBlocker plugin = CommandBlocker.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("cmdblock.reload") || sender.isOp()) {

            Bukkit.getLogger().info("Reloading configs...");

            plugin.setConfig(new Config());
            plugin.reloadConfig();

            if (sender instanceof Player) Bukkit.getLogger().info("Config reloaded");
            sender.sendMessage(("&aConfig reloaded successfully"));
        }
        return false;
    }
}