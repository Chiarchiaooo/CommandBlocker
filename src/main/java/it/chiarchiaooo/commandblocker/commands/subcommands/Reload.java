package it.chiarchiaooo.commandblocker.commands.subcommands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.commands.ACommand;
import it.chiarchiaooo.commandblocker.services.ConfigService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Reload extends ACommand {

    private final ConfigService configService = main.getConfigService();

    public Reload(CommandBlocker main) {
        super(main);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("cmdblock.reload") || sender.isOp()) {

            Bukkit.getLogger().info("Reloading configs...");

            main.reloadConfig();
            configService.makeConfigs();
            configService.setMessages();
            configService.setLists();


            if (sender instanceof Player) Bukkit.getLogger().info("Config reloaded");
            sender.sendMessage(("&aConfig reloaded successfully"));
        }
        return false;
    }
}