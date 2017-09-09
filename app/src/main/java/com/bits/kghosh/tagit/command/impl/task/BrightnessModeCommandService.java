package com.bits.kghosh.tagit.command.impl.task;

import android.content.Context;
import android.provider.Settings;

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

public class BrightnessModeCommandService implements CommandService {

    private static String MODE_KEY = "BRST";
    private static String VALUE_0 = "0";
    private static String VALUE_25 = "25";
    private static String VALUE_50 = "50";
    private static String VALUE_75 = "75";
    private static String VALUE_100 = "100";
    private static String VALUE_AUTO = "auto";

    private Context context;
    private Map<String, SubCommand> subCommandMap;
    private List<SubCommand> subCommands;

    public BrightnessModeCommandService(Context context) {
        this.context = context;
        subCommandMap = new LinkedHashMap<>();
        subCommands = new ArrayList<>();

        initializeSubCommands();
    }

    private void initializeSubCommands() {
        // Subcommand to set brightness value
        SubCommand subCommand = new SubCommand();
        subCommand.setKey(MODE_KEY);
        subCommand.setValue(false);
        subCommand.setDescription("Set brightness value of your screen");

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
        if (subCommand.getKey().equals(MODE_KEY)) {
            return setBrightnessMode(subCommand.getValue());
        } else {
            return false;
        }
    }

    private boolean setBrightnessMode(Object data) {
        if (data instanceof String) {
            String value = data.toString();
            if (value == VALUE_0) {
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS,
                        0);
            } else if (value == VALUE_25) {
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS,
                        25);
            } else if (value == VALUE_50) {
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS,
                        50);
            } else if (value == VALUE_75) {
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS,
                        75);
            } else if (value == VALUE_100) {
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS,
                        100);
            } else if (value == VALUE_AUTO) {
                Settings.System.putInt(
                        context.getContentResolver(),
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
            } else {
                return false;
            }
        }
        return false;
    }
}
