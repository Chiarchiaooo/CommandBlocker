package it.chiarchiaooo.commandblocker.Managers;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.utils.Messages;
import it.chiarchiaooo.commandblocker.utils.Vars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;


//The class will implement CommandExecutor.
public class Commands implements CommandExecutor {

    private final Vars vars = CommandBlocker.getVars();
    private final Messages msgHandler = CommandBlocker.getMsgHandler();

    @SuppressWarnings("all")
    public interface MainCmd { boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args); }



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, String[] args) {

        if (args.length == 0) sender.sendMessage("&6CommandBlocker &7&l- &ePlugin made by &6&lChiarchiaooo, type &6\"/cmdblocker help\"");

        else {
            String subCmd = args[0];

            if (!isCmdRegistered(subCmd)) sender.sendMessage(msgHandler.sendMsg(vars.getNoArgsErrorMsg()));

            else if (!isPlayerExecutor(sender) && !subCmd.equals("help") && !subCmd.equals("reload")) Messages.sendConsoleErrorMessage();

            else if (isPlayerExecutor(sender) && !hasPermission((Player) sender,subCmd)) sender.sendMessage(msgHandler.sendMsg(vars.getCmdBlockedMsg()));

            else runCommand(sender,cmd,commandLabel,args);
        }

        return true;
    }

    private boolean isCmdRegistered(String subCmdName) {return vars.getCOMMANDS().containsKey(subCmdName);}

    private boolean isPlayerExecutor(CommandSender sender) {return sender instanceof Player;}

    private boolean hasPermission(Player p, String cmd) {return p.hasPermission("cmdblock."+cmd+".command") || cmd.equals("help");}

    private void runCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {vars.getCOMMANDS().get(args[0]).onCommand(sender,cmd,commandLabel,Arrays.copyOfRange(args, 1, args.length));}
}
