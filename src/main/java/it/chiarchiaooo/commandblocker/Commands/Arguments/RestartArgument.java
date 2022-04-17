package it.chiarchiaooo.commandblocker.Commands.Arguments;

import it.chiarchiaooo.commandblocker.utils.CommandHandler;
import it.chiarchiaooo.commandblocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RestartArgument implements CommandHandler.CommandInterface {

    private Main plugin;

    public RestartArgument(Main pl) {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("cmdblock.reload")) {
            sender.sendMessage(" ");
            plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aPlugin restart forced"));
            plugin.onDisable();
            plugin.onEnable();
        }
        return false;
    }
}