package it.chiarchiaooo.commandblocker.Commands.Arguments;

import it.chiarchiaooo.commandblocker.Commands.Event.SubCommand;
import it.chiarchiaooo.commandblocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RestartArgument extends SubCommand {
    public String getName() {
        return "restart";
    }

    public void perform(Player p, String[] args) {
        if (p.hasPermission("cmdblock.restart")) {
            p.sendMessage(" ");
            p.sendMessage(format("&aQua scrivi il messaggio che vuoi per il force restart"));
            Main.getInstance().restartPlugin();
        } else {
            p.sendMessage(" ");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&6&lCommandBlocker &6&lDeveloper: "));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&7» &fChiarchiaooo"));
            p.sendMessage(" ");
        }
    }
    private static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}