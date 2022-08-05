package it.chiarchiaooo.commandblocker.utils;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import it.chiarchiaooo.commandblocker.Commands.HelpArgument;
import it.chiarchiaooo.commandblocker.Commands.ReloadArgument;
import it.chiarchiaooo.commandblocker.Commands.RestartArgument;
import it.chiarchiaooo.commandblocker.Managers.Commands;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Vars {

    private Map<String, Commands.MainCmd> COMMANDS = new HashMap<>() {{

        put("restart", new RestartArgument());
        put("reload", new ReloadArgument());
        put("help", new HelpArgument());

    }};

    private String HELP_STRING = 
            """

                    §6§lCommandBlocker §8- Help:
                    §6Version: §f%version%
                    
                    Available Commands:
                    §8» §6§o/cmdblock help §8- §fShows this list
                    §8» §6§o/cmdblock reload §8- §fReload plugin configuration.
                    §8» §6§o/cmdblock restart §8- §fForce restart a plugin.
                    
                    """.replace("%version%", CommandBlocker.getInstance().getDescription().getVersion());


    private Boolean areSingleCommandEnabled;
    private Boolean areCommandGroupsEnabled;
    private Boolean isTabBlockingEnabled;
    private Boolean isPapiPresent;

    private final String GENERAL_CMD_BYPASS = "cmdblock.bypass.*";
    private String NoArgsErrorMsg;
    private String cmdBlockedMsg;
    private String prefix;

    private List<String> SingleCmdWhitelist;
    private List<String> cmdWhitelist;
    private List<String> cmdGroups;

    private final Map<String,List<String>> CmdGroupCommands = new HashMap<>();


}
