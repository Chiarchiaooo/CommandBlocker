package it.chiarchiaooo.commandblocker.services;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MsgService {

    private CommandBlocker main;
    private final VarService varService;

    public MsgService(CommandBlocker main) {
        this.main = main;
        this.varService = this.main.getVarService();
        varService.setPapiPresent(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);
    }

    public String setPlaceholders(Player p, String s) {
        String msg = s.replace("%prefix%", varService.getPrefix());

        if (varService.isPapiPresent() && p != null)
            return PlaceholderAPI.setPlaceholders(p, msg.replace("%player%", p.getName()) );

        else
            return s.replace("%player%","Console")
                    .replaceAll(PlaceholderAPI.getPlaceholderPattern().pattern(),"");

    }

    public String formatMsg(Player p, String s) {return setPlaceholders(p, color(s));}

    public String formatMsg(String s) {return setPlaceholders(null, color(s));}

    public String color(String s) {return ChatColor.translateAlternateColorCodes('&',s);}
}