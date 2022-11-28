package it.chiarchiaooo.commandblocker.commands.subcommands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.commands.ACommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Reload extends ACommand {

    public Reload(CommandBlocker main) {
        super(main);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("cmdblock.reload") || sender.isOp()) {

            Bukkit.getLogger().info("Reloading configs...");

            main.reloadConfig();

            if (sender instanceof Player) Bukkit.getLogger().info("Config reloaded");
            sender.sendMessage(("&aConfig reloaded successfully"));
        }
        return false;
    }
}