package com.bits.kghosh.tagit.services.dto;

import com.bits.kghosh.tagit.model.Command;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public class CommandDTO {
    private static final CommandDTO ourInstance = new CommandDTO();
    private Command command;

    public static CommandDTO getInstance() {
        return ourInstance;
    }

    private CommandDTO() {
    }

    public Command getCommandTransfered() {
        return command;
    }

    public void setCommandToTransfer(Command command) {
        this.command = command;
    }
}
