package com.bits.kghosh.tagit.model;

import java.util.List;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class Command {

    private CommandInfo commandInfo;
    private List<SubCommand> allSubCommands;

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    public void setCommandInfo(CommandInfo commandInfo) {
        this.commandInfo = commandInfo;
    }

    public List<SubCommand> getAllSubCommands() {
        return allSubCommands;
    }

    public void setAllSubCommands(List<SubCommand> allSubCommands) {
        this.allSubCommands = allSubCommands;
    }
}
