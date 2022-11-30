package it.chiarchiaooo.commandblocker.services;

import it.chiarchiaooo.commandblocker.CommandBlocker;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class VarService {

    private final CommandBlocker main;

    public VarService(CommandBlocker main) {
        this.main = main;
        initStrings();
    }

    private boolean singleCommandEnabled;
    private boolean commandGroupsEnabled;
    private boolean tabBlockingEnabled;
    private boolean papiPresent;

    private String cmdBypassPermission;
    private String NoArgsErrorMsg;
    private String cmdBlockedMsg;
    private String helpMessage;
    private String prefix;

    private List<String> SingleCmdWhitelist;
    private List<String> cmdWhitelist;

    // Key: group permission, value: group cmds
    private final Map<String,List<String>> CmdGroupCommands = new HashMap<>();

    private void initStrings() {
        cmdBypassPermission = "cmdblock.bypass.*";
        helpMessage = this.main.getMsgService().formatMsg(
            "&6&lCommandBlocker &8- Help:\n"+
            "&6Version: &f%version%\n"+
            "\n"+
            "Available commands:\n"+
            "&8» &6&o/cmdblock help &8- &fShows this list\n"+
            "&8» &6&o/cmdblock reload &8- &fReload plugin configuration.\n"+
            "&8» &6&o/cmdblock restart &8- &fForce restart a plugin.\n"

        ).replace("%version%", this.main.getDescription().getVersion());
    }
}
