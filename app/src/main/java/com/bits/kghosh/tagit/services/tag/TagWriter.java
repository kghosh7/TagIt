package com.bits.kghosh.tagit.services.tag;

import com.bits.kghosh.tagit.command.CommandService;
import com.bits.kghosh.tagit.command.CommandServiceFactory;
import com.bits.kghosh.tagit.exceptions.InvalidTagDataException;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.Tag;

import java.util.List;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class TagWriter {

    CommandServiceFactory factory;

    public TagWriter() {
        this.factory = new CommandServiceFactory();
    }

    public void write(Tag tag) throws InvalidTagDataException {
        if (tag == null || tag.getCommands() == null || tag.getCommands().size() < 1) {
            throw new InvalidTagDataException("No data to write");
        }

        if (tag.isHasTasks()) {
            writeTasks(tag.getCommands());
        } else if (tag.isHasAction()) {
            writeAction(tag.getCommands());
        }
    }

    private void writeTasks(List<Command> commands) {

    }

    private void writeAction(List<Command> commands) {
        Command command = commands.get(0);
        if (command != null) {
            //final CommandService commandService = factory.getCommandService(command.getCommandInfo().getCommand());
            //final Object content = commandService.getTagWritableCommandObject(command);

            //logic to write

        }
    }
}
