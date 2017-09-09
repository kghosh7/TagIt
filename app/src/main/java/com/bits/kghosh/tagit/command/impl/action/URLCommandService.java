package com.bits.kghosh.tagit.command.impl.action;

import android.content.Context;

import com.bits.kghosh.tagit.command.CommandService;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.SubCommand;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class URLCommandService implements CommandService {

    private static String URL = "URUR";

    private Context context;
    private Map<String, SubCommand> subCommandMap;
    private List<SubCommand> subCommands;

    public URLCommandService(Context context) {
        this.context = context;
        subCommandMap = new LinkedHashMap<>();
        subCommands = new ArrayList<>();

        initializeSubCommands();
    }

    private void initializeSubCommands() {
        // Subcommand to launch URI
        SubCommand subCommand = new SubCommand();
        subCommand.setKey(URL);
        subCommand.setValue("http://www.facebook.com");
        subCommand.setDescription("Write URL to your tag");

        subCommands.add(subCommand);
        subCommandMap.put(subCommand.getKey(), subCommand);
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
    public List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
