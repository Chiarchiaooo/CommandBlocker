package it.chiarchiaooo.commandblocker.Commands.Event;

import org.bukkit.entity.Player;

public abstract class SubCommand {
    public abstract String getName();

    public abstract boolean perform(Player paramPlayer, String[] paramArrayOfString);
}