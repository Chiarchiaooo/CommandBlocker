package it.chiarchiaooo.commandblocker.Commands;

import it.chiarchiaooo.commandblocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;

public class ReloadCommand implements CommandExecutor {

    String prefix = Main.getInstance().getConfig().getString("prefix");

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("vanillacore.reload") || sender.isOp()) {
                Main.getInstance().getLogger().info("Reloading configs...");
                Main.getInstance().reloadConfig();
                Main.getInstance().saveConfig();
                Main.getInstance().onDisable();
                Main.getInstance().onEnable();
                Main.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aConfig reloaded"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aPlugin reloaded Successfully"));
            }
        } else {
            Main.getInstance().getLogger().info("Reloading configs...");
            Main.getInstance().reloadConfig();
            Main.getInstance().saveConfig();
            Main.getInstance().onDisable();
            Main.getInstance().onEnable();
            Main.getInstance().getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aConfig reloaded"));
        }
        return true;
    }
}