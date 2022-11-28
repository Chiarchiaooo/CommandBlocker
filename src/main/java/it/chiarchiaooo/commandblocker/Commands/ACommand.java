package it.chiarchiaooo.commandblocker.commands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class ACommand {

    protected CommandBlocker main;

    public ACommand(CommandBlocker main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }
}
