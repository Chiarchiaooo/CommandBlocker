package it.chiarchiaooo.commandblocker.commands.subcommands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.commands.ACommand;
import it.chiarchiaooo.commandblocker.services.ConfigService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

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
        if (args.length < 1) {
            sender.sendMessage(main.getVarService().getResetConfirmMessage());
            return true;

        } else if (!args[0].equals("CONFIRM")) {
            sender.sendMessage(main.getVarService().getResetConfirmMessage());
            return true;
        }

        long timestampStart = System.currentTimeMillis();
        main.getLogger().info("Resetting configs...");


        if (!new File(main.getDataFolder(), "config.yml").delete()) {
            sender.sendMessage("§cAn error occred while resetting the configs: Cannot delete plugin folder");
            return true;
        }

        configService.makeConfigs();

        configService.setMessages();
        configService.setBools();
        configService.setLists();

        int processingTime = (int) (System.currentTimeMillis() - timestampStart);
        if (sender instanceof Player) main.getLogger().info("Config reloaded (in " + processingTime + " ms)");
        sender.sendMessage("§aConfig resetted successfully (in " + processingTime + " ms)");
        return true;
    }
}
