package it.chiarchiaooo.commandblocker.services;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class MsgService {

    private CommandBlocker main;
    private final VarService varService;

    public MsgService(CommandBlocker main) {
        this.main = main;
        this.varService = this.main.getVarService();
        varService.setPapiPresent(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null);
    }

    public String sendMsg(Player p, String s) {
        s = ChatColor.translateAlternateColorCodes('&',s);
        return setPlaceholders(p,s);
    }

    public String sendMsg(String s) {return ChatColor.translateAlternateColorCodes('&',s);}

    public String setPlaceholders(Player p,String s) {
        s = s.replace("%prefix%", varService.getPrefix());
        
        if (varService.isPapiPresent() && p != null) return PlaceholderAPI.setPlaceholders(p, s.replace("%player%", p.getName()));

        else return s.replace("%player","Console").replaceAll(PlaceholderAPI.getPlaceholderPattern().pattern(),"");
    }

    public static void sendConsoleErrorMessage() { Bukkit.getLogger().log(Level.SEVERE,"Console cannot execute this command");}

}