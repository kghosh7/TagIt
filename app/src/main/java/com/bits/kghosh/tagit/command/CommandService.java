package com.bits.kghosh.tagit.command;

import com.bits.kghosh.tagit.model.SubCommand;

import java.util.List;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public interface CommandService {
    public SubCommand getConfigItem();

    public boolean execute(SubCommand command);

    public boolean execute(List<SubCommand> commands);
}
