package it.chiarchiaooo.commandblocker.commands;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.commands.subcommands.Help;
import it.chiarchiaooo.commandblocker.commands.subcommands.Reload;
import it.chiarchiaooo.commandblocker.commands.subcommands.Reset;
import it.chiarchiaooo.commandblocker.services.MsgService;
import it.chiarchiaooo.commandblocker.services.VarService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (args.length == 0)
            sender.sendMessage(msgService.formatMsg("&6CommandBlocker &7&l- &ePlugin made by &6&lChiarchiaooo, type &6\"/cmdblocker help\" for help"));

        else {
            String subCmd = args[0];

            if (!checkPermission(sender, subCmd))
                sender.sendMessage(msgService.formatMsg(varService.getCmdBlockedMsg()));

            else runCommand(sender, cmd, commandLabel, args);
        }

        return true;
    }


    private boolean checkPermission(CommandSender sender, String cmd) {
        return sender.hasPermission("cmdblock." + cmd + ".command") || cmd.equals("help");
    }

    private void runCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ACommand aCmd;

        switch (args[0]) {
            default -> {
                sender.sendMessage(msgService.formatMsg(varService.getNoArgsErrorMsg()));
                return;
            }

            case "help" -> aCmd = new Help(this.main);

            case "reload" -> aCmd = new Reload(this.main);

            case "reset" -> aCmd = new Reset(this.main);
        }

        aCmd.onCommand(sender, cmd, commandLabel, Arrays.copyOfRange(args, 1, args.length));
    }
}
