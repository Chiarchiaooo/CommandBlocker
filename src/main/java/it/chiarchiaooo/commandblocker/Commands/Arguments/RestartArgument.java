package it.chiarchiaooo.commandblocker.Commands.Arguments;

import it.chiarchiaooo.commandblocker.CommandHandler;
import it.chiarchiaooo.commandblocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RestartArgument implements CommandHandler.CommandInterface {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("cmdblock.reload")) {
            sender.sendMessage(" ");
            Main.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aPlugin restart forced"));
            Main.getInstance().onDisable();
            Main.getInstance().onEnable();
        }
        return false;
    }
}