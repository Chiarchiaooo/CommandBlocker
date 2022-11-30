package it.chiarchiaooo.commandblocker.commands.subcommands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.commands.ACommand;
import it.chiarchiaooo.commandblocker.services.ConfigService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reset extends ACommand {

    private final CommandBlocker main;
    private final ConfigService configService;

    public Reset(CommandBlocker main) {
        super(main);

        this.main = main;
        configService = main.getConfigService();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!args[0].equals("CONFIRM")) {
            sender.sendMessage(main.getVarService().getResetConfirmMessage());
            return true;
        }

        main.getLogger().info("Resetting configs...");

        if (!main.getDataFolder().delete()) {
            sender.sendMessage("§An error occred while resetting the configs: Cannot delete folder \"CommandBlocker\"");
            return true;
        }

        configService.makeConfigs();
        configService.setMessages();
        configService.setBools();
        configService.setLists();


        if (sender instanceof Player) main.getLogger().info("Config reloaded");
        sender.sendMessage(("&aConfig reloaded successfully"));
        return true;
    }
}
