package it.chiarchiaooo.commandblocker.Managers;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {



    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String alias, String[] args) {
        if (args.length ==1) return new ArrayList<>(CommandBlocker.getVars().getCOMMANDS().keySet());
        return new ArrayList<>();
    }
}