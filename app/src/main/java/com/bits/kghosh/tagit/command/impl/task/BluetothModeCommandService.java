package com.bits.kghosh.tagit.command.impl.task;

import android.bluetooth.BluetoothAdapter;
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

public class BluetothModeCommandService implements CommandService {

    private static String MODE = "BTST";

    private Context context;
    private Map<String, SubCommand> subCommandMap;
    private List<SubCommand> subCommands;

    public BluetothModeCommandService(Context context) {
        this.context = context;
        subCommandMap = new LinkedHashMap<>();
        subCommands = new ArrayList<>();

        initializeSubCommands();
    }

    private void initializeSubCommands() {
        // Subcommand to set bluetooth to on or off
        SubCommand subCommand = new SubCommand();
        subCommand.setKey(MODE);
        subCommand.setValue(false);
        subCommand.setDescription("Enable or disable bluetooth on your mobile");

        subCommands.add(subCommand);
        subCommandMap.put(subCommand.getKey(), subCommand);
    }

    @Override
    public boolean execute(Command command) {
        boolean success = false;

        if (command != null) {
            final List<SubCommand> subCommands = command.getSubCommands();
            if (subCommands != null && subCommands.size() > 0) {
                for (int i = 0; i < subCommands.size(); i++) {
                    success = success || executeSubCommand(subCommands.get(i));
                }
            }
        }

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

    private boolean executeSubCommand(SubCommand subCommand) {
        if (subCommand.getKey().equals(MODE)) {
            return setBluetoothMode((Boolean) subCommand.getValue());
        } else {
            return false;
        }
    }

    private boolean setBluetoothMode(Boolean data) {
        boolean isActive = data == true;
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null) {
            if (isActive) {
                adapter.enable();
            } else {
                adapter.disable();
            }
        }
        return false;
    }
}
