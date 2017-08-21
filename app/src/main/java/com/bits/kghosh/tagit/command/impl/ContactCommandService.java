package com.bits.kghosh.tagit.command.impl;

import android.text.TextUtils;

import com.bits.kghosh.tagit.command.CommandService;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.SubCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class ContactCommandService implements CommandService {

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

//    @Override
//    public String getTagWritableCommandObject(Command command) {
//        String contentString = null;
//        Collection<String> contents = new ArrayList<>();
//        if (command != null) {
//            final List<SubCommand> subCommands = command.getSubCommands();
//            if (subCommands == null || subCommands.size() < 1) {
//                return null;
//            } else {
//                for (int i = 0; i < subCommands.size(); i++) {
//                    final String subCommandString = getTagWritableSubcommandString(subCommands.get(i));
//                    if (subCommandString != null) {
//                        contents.add(subCommandString);
//                    }
//                }
//                contentString = TextUtils.join(";", contents);
//            }
//        }
//        return contentString;
//    }

    @Override
    public List<SubCommand> getSubCommands() {
        List<SubCommand> subCommands = new ArrayList<>();

        return null;
    }


//    private String getTagWritableSubcommandString(SubCommand subCommand) {
//        return null;
//    }
}
