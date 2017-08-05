package com.bits.kghosh.tagit.model;

import java.util.List;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class TaskConfig {

    private CommandInfo commandInfo;
    private List<SubCommand> subCommandsToExecute;

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    public void setCommandInfo(CommandInfo commandInfo) {
        this.commandInfo = commandInfo;
    }

    public List<SubCommand> getSubCommandsToExecute() {
        return subCommandsToExecute;
    }

    public void setSubCommandsToExecute(List<SubCommand> subCommandsToExecute) {
        this.subCommandsToExecute = subCommandsToExecute;
    }
}
