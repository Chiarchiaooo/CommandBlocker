package it.chiarchiaooo.commandblocker.Commands.Arguments;

import it.chiarchiaooo.commandblocker.CommandHandler;
import it.chiarchiaooo.commandblocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadArgument implements CommandHandler.CommandInterface {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("cmdblock.reload") || sender.isOp()) {
            Main.getInstance().getLogger().info("Reloading configs...");
            Main.getInstance().reloadConfig();
            if (sender instanceof Player) Main.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aConfig reloaded"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlugin reloaded Successfully"));
        }
        return false;
    }
}