package com.bits.kghosh.tagit.model;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public class CommandInfo {
    private CommandsEnum command;
    private String name;
    private String description;

    public CommandsEnum getCommand() {
        return command;
    }

    public void setCommand(CommandsEnum command) {
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
