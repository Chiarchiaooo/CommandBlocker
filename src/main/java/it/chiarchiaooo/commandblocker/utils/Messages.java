package it.chiarchiaooo.commandblocker.utils;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class Messages {
    
    private final Vars vars = CommandBlocker.getVars();

    public Messages() {vars.setIsPapiPresent(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);}

    public String sendMsg(Player p, String s) {
        s = ChatColor.translateAlternateColorCodes('&',s);
        return setPlaceholders(p,s);
    }

    public String sendMsg(String s) {return ChatColor.translateAlternateColorCodes('&',s);}

    public String setPlaceholders(Player p,String s) {
        s = s.replace("%prefix%",vars.getPrefix());
        
        if (vars.getIsPapiPresent() && p != null) return PlaceholderAPI.setPlaceholders(p, s.replace("%player%", p.getName()));

        else return s.replace("%player","Console").replaceAll(PlaceholderAPI.getPlaceholderPattern().pattern(),"");
    }

    public static void sendConsoleErrorMessage() {CommandBlocker.getInstance().getLogger().log(Level.SEVERE,"Console cannot execute this command");}

}