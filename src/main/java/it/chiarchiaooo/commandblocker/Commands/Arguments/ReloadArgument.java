package it.chiarchiaooo.commandblocker.Commands.Arguments;

import it.chiarchiaooo.commandblocker.Commands.Event.SubCommand;
import it.chiarchiaooo.commandblocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReloadArgument extends SubCommand {
    public String getName() {
        return "reload";
    }

    public void perform(Player p, String[] args) {
        if (p.hasPermission("cmdblock.reload") || p.hasPermission("*") || p.isOp()) {
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"Plugin ricaricato con successo."));
            Main.getInstance().reloadPlugin();
        } else {
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker &6&lDeveloper: "));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7» &fChiarchiaooo"));
            p.sendMessage(" ");
        }
    }
}