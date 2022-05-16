package it.chiarchiaooo.commandblocker.Commands.Arguments;

import it.chiarchiaooo.commandblocker.utils.CommandHandler;
import it.chiarchiaooo.commandblocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class ReloadArgument implements CommandHandler.CommandInterface {

    private final Main plugin;

    public ReloadArgument(Main pl) {
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("cmdblock.reload") || sender.isOp()) {
            plugin.getLogger().info("Reloading configs...");
            plugin.reloadConfig();
            if (sender instanceof Player) plugin.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aConfig reloaded"));
            sender.sendMessage(plugin.sendmsg(null,"&aPlugin reloaded Successfully"));
        }
        return false;
    }
}