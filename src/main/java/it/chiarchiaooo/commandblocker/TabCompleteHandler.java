package it.chiarchiaooo.commandblocker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;

public class TabCompleteHandler implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length ==1) return new ArrayList<>(CommandHandler.commands.keySet());
        return new ArrayList<>();
    }
}