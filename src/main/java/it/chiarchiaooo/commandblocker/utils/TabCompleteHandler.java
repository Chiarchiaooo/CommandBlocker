package it.chiarchiaooo.commandblocker.utils;

import it.chiarchiaooo.commandblocker.Main;
import it.chiarchiaooo.commandblocker.utils.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabCompleteHandler implements TabCompleter {

    private final HashMap<String, CommandHandler.CommandInterface> commands;

    public TabCompleteHandler(Main pl) {
        this.commands = pl.commands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args.length ==1) return new ArrayList<>(commands.keySet());
        return new ArrayList<>();
    }
}