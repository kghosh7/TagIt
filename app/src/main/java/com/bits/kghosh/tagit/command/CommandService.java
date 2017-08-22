package com.bits.kghosh.tagit.command;

import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.NdefRecordTypeEnum;
import com.bits.kghosh.tagit.model.SubCommand;

import java.util.List;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public interface CommandService {
    public List<SubCommand> getSubCommands();

    public boolean execute(Command command);

    public boolean execute(List<Command> commands);

    public Object getDataToWrite(Command command);

    public NdefRecordTypeEnum getRecordType();
}
