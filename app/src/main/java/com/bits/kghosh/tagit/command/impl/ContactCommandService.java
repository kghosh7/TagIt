package com.bits.kghosh.tagit.command.impl;

import com.bits.kghosh.tagit.command.CommandService;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.NdefRecordTypeEnum;
import com.bits.kghosh.tagit.model.SubCommand;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class ContactCommandService implements CommandService {

    private Map<String, SubCommand> subCommandMap;
    List<SubCommand> subCommands;

    public ContactCommandService() {
        subCommandMap = new LinkedHashMap<>();
        subCommands = new ArrayList<>();

        initializeSubCommands();
    }

    private void initializeSubCommands() {
        SubCommand subCommand = new SubCommand();
        subCommand.setKey("TLTL");
        subCommand.setValue("9850297030");
        subCommand.setDescription("Write telephone number to your tag");

        subCommands.add(subCommand);
        subCommandMap.put("TLTL", subCommand);
    }

    @Override
    public boolean execute(Command command) {
        boolean success = false;
        return success;
    }

    @Override
    public boolean execute(List<Command> commands) {
        boolean success = false;
        if (commands != null) {
            for (int i = 0; i < commands.size(); i++) {
                success = success && execute(commands.get(i));
            }
        }
        return success;
    }

    @Override
    public Object getDataToWrite(Command command) {
        if (command != null) {
            List<SubCommand> subCommands = command.getSubCommands();
            if (subCommands != null && subCommands.size() > 0) {
                SubCommand comm = subCommands.get(0);
                return comm.getValue();
            }
        }
        return null;
    }

    @Override
    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public NdefRecordTypeEnum getRecordType() {
        return NdefRecordTypeEnum.TELEPHONE;
    }
}
