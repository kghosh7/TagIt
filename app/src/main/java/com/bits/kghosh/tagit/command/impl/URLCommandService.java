package com.bits.kghosh.tagit.command.impl;

import com.bits.kghosh.tagit.command.CommandService;
import com.bits.kghosh.tagit.model.SubCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class URLCommandService implements CommandService {

    @Override
    public boolean execute(SubCommand command) {
        boolean success = false;
        return success;
    }

    @Override
    public boolean execute(List<SubCommand> commands) {
        boolean success = false;
        if (commands != null) {
            for (int i = 0; i < commands.size(); i++) {
                success = success && execute(commands.get(i));
            }
        }
        return success;
    }

    @Override
    public List<SubCommand> getSubCommands() {
        List<SubCommand> subCommands = new ArrayList<>();

        SubCommand subCommand = new SubCommand();
        subCommand.setKey("UR-UR");
        subCommand.setDescription("Write URL to your tag");
        subCommands.add(subCommand);
        
        return subCommands;
    }
}
