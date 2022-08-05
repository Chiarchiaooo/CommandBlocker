package it.chiarchiaooo.commandblocker.Commands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.Managers.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpArgument implements Commands.MainCmd {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        sender.sendMessage( CommandBlocker.getMsgHandler().sendMsg( CommandBlocker.getVars().getHELP_STRING() ) );

        return false;
    }
}