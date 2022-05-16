package it.chiarchiaooo.commandblocker.utils;

import it.chiarchiaooo.commandblocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;



//The class will implement CommandExecutor.
public class CommandHandler implements CommandExecutor {

    private final FileConfiguration config;
    public static HashMap<String, CommandInterface> commands = new HashMap<>();

    public CommandHandler(Main pl) {
        this.config = pl.getConfig();
    }

    //IMPORTANT: This is an interface, not a class.
    public interface CommandInterface {

        //Every time I make a command, I will use this same method.
        boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);

    }


    //This is where we will store the commands

    //Register method. When we register commands in our onEnable() we will use this.
    public void register(String name, CommandInterface cmd) {

        //When we register the command, this is what actually will put the command in the hashmap.
        commands.put(name, cmd);
    }


    //This will be a template. All commands will have this in common.
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(args.length == 0) {
            commands.get("cmdblock").onCommand(sender, cmd, commandLabel, args);
            return true;
        }else{
                //If that argument exists in our registration in the onEnable(); || !(sender.hasPermission())
            if(commands.containsKey(args[0]) && !(args[0].equalsIgnoreCase("cmblock"))){
                    //Get The executor with the name of args[0]. With our example, the name of the executor will be args because in
                    //the command /example args, args is our args[0].
                    commands.get(args[0]).onCommand(sender, cmd, commandLabel, args);
            } else {
                //We want to send a message to the sender if the command doesn't exist.
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("cmd-arg-not-found-message").replace("%prefix%",config.getString("prefix")).replace("%arg%",args[0])));
            }
        }
        return true;
    }
}
