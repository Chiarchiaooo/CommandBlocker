package it.chiarchiaooo.commandblocker.commands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.commands.subcommands.Help;
import it.chiarchiaooo.commandblocker.commands.subcommands.Reload;
import it.chiarchiaooo.commandblocker.services.MsgService;
import it.chiarchiaooo.commandblocker.services.VarService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;


//The class will implement CommandExecutor.
public class MainCommand extends ACommand implements CommandExecutor {

    private final VarService varService;
    private final MsgService msgService;

    public MainCommand(CommandBlocker main) {
        super(main);
        this.varService = this.main.getVarService();
        this.msgService = this.main.getMsgService();
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, String[] args) {

        if (args.length == 0)
            sender.sendMessage("&6CommandBlocker &7&l- &ePlugin made by &6&lChiarchiaooo, type &6\"/cmdblocker help\"");

        else {
            String subCmd = args[0];
            if (!isPlayerExecutor(sender) && !subCmd.equals("help") && !subCmd.equals("reload"))
                MsgService.sendConsoleErrorMessage();

            else if (isPlayerExecutor(sender) && !hasPermission((Player) sender, subCmd))
                sender.sendMessage(msgService.sendMsg(varService.getCmdBlockedMsg()));

            else runCommand(sender, cmd, commandLabel, args);
        }

        return true;
    }

    private boolean isPlayerExecutor(CommandSender sender) {
        return sender instanceof Player;
    }

    private boolean hasPermission(Player p, String cmd) {
        return p.hasPermission("cmdblock." + cmd + ".command") || cmd.equals("help");
    }

    private void runCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ACommand acmd;
        switch (args[0]) {
            default:
                sender.sendMessage(msgService.sendMsg(varService.getNoArgsErrorMsg()));
                return;
            case "help":
                acmd = new Help(this.main);
                break;
            case "reload":
                acmd = new Reload(this.main);
                break;
        }
        acmd.onCommand(sender, cmd, commandLabel, Arrays.copyOfRange(args, 1, args.length));
    }
}
