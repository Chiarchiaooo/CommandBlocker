package it.chiarchiaooo.commandblocker.commands.subcommands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.commands.ACommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Help extends ACommand {

    public Help(CommandBlocker main) {
        super(main);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        sender.sendMessage( this.main.getMsgService().formatMsg(this.main.getVarService().getHelpMessage()));
        return false;
    }
}