package com.bits.kghosh.tagit.command.impl;

import com.bits.kghosh.tagit.command.CommandService;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.NdefRecordTypeEnum;
import com.bits.kghosh.tagit.model.SubCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class ContactCommandService implements CommandService {

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
        return null;
    }

    @Override
    public List<SubCommand> getSubCommands() {
        List<SubCommand> subCommands = new ArrayList<>();

        return null;
    }

    @Override
    public NdefRecordTypeEnum getRecordType() {
        return NdefRecordTypeEnum.TEXT;
    }
}
