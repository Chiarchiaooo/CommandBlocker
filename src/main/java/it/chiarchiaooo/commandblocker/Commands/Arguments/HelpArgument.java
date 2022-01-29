package it.chiarchiaooo.commandblocker.Commands.Arguments;

import it.chiarchiaooo.commandblocker.Commands.Event.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpArgument extends SubCommand {
    public String getName() {
        return "help";
    }

    public void perform(Player player, String[] args) {
        if (player.hasPermission("*") || player.isOp()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&'," "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker &8»"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&'," "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Api-Version: &f1.0"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6Core-Version: &f1.0"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&'," "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/cmdblock help &8- &fList of all commands"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/cmdblock reload &8- &fReload plugin configuration. "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&o/cmdblock restart &8- &fForce restart a plugin."));
        } else {
            player.sendMessage(" ");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker &6&lDeveloper: "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7» &fChiarchiaooo"));
            player.sendMessage(" ");
        }
    }
}