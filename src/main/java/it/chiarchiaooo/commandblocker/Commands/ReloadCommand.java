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
        Logger log = getLogger();

        if (sender instanceof Player && (sender.hasPermission("cmdblock.reload") || sender.isOp())) {
            log.info("Reloading configs...");
            Main.getInstance().onDisable();
            Main.getInstance().onEnable();
            Player p = (Player) sender;
            log.info(ChatColor.translateAlternateColorCodes('&',"&aConfigs reloaded "));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + " &aPlugin ricaricato con successo"));
        } else {
            log.info("Reloading configs...");
            Main.getInstance().onDisable();
            Main.getInstance().onEnable();
            log.info(ChatColor.translateAlternateColorCodes('&',"&aConfigs reloaded"));
        }
        return true;
    }
}