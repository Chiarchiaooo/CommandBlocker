package it.chiarchiaooo.commandblocker.commands.subcommands;

import it.chiarchiaooo.commandblocker.services.ConfigService;
import it.chiarchiaooo.commandblocker.commands.ACommand;
import it.chiarchiaooo.commandblocker.CommandBlocker;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;


public class Reload extends ACommand {

    private final ConfigService configService = main.getConfigService();

    public Reload(CommandBlocker main) {
        super(main);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        long timestampStart = System.currentTimeMillis();
        main.getLogger().info("Reloading configs...");

        main.reloadConfig();
        configService.setMessages();
        configService.setBools();
        configService.setLists();

        int processingTime = (int) (System.currentTimeMillis() - timestampStart);
        if (sender instanceof Player) main.getLogger().info("Config reloaded (in "+ processingTime+" ms)");
        sender.sendMessage(("§aConfig reloaded successfully (in "+ processingTime+" ms)"));

        return false;
    }
}