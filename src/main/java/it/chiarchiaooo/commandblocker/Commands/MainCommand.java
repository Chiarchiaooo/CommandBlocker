package it.chiarchiaooo.commandblocker.Commands;

import it.chiarchiaooo.commandblocker.CommandHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandHandler.CommandInterface {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >1) return true;
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lCommandBlocker &7&l- &eMade By &6&lChiarchiaooo &8- &eType &e&n/cmdblock help &efor help."));
        return true;
    }
}