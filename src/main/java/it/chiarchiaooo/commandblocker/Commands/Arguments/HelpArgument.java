package it.chiarchiaooo.commandblocker.Commands.Arguments;

import it.chiarchiaooo.commandblocker.CommandHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpArgument implements CommandHandler.CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender.hasPermission("*") || sender.isOp())) return true;
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&'," "));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker &8»"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&'," "));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Version: &f1.4"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&'," "));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/cmdblock help &8- &fShows this list"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/cmdblock reload &8- &fReload plugin configuration. "));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/cmdblock restart &8- &fForce restart a plugin."));
        return false;
    }
}