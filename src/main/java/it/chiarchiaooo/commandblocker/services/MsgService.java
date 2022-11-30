package it.chiarchiaooo.commandblocker.services;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MsgService {

    private final VarService varService;

    public MsgService(CommandBlocker main) {
        this.varService = main.getVarService();
        varService.setPapiPresent(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);
    }

    public String setPlaceholders(Player p, String s) {
        String msg = s.replace("%prefix%", varService.getPrefix());

        if (varService.isPapiPresent() && p != null)
            return PlaceholderAPI.setPlaceholders(p, msg.replace("%player%", p.getName()));

        else
            return msg.replace("%player%", "Console");
    }

    public String formatMsg(String s) {
        return setPlaceholders(null, color(s));
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}