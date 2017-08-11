package com.bits.kghosh.tagit.model;

import java.util.List;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class Command {

    private CommandInfo commandInfo;
    private List<SubCommand> subCommands;
    private CommandTypeEnum type = CommandTypeEnum.ACTION;

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    public void setCommandInfo(CommandInfo commandInfo) {
        this.commandInfo = commandInfo;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public void setSubCommands(List<SubCommand> subCommands) {
        this.subCommands = subCommands;
    }

    public CommandTypeEnum getType() {
        return type;
    }

    public void setType(CommandTypeEnum type) {
        this.type = type;
    }
}
