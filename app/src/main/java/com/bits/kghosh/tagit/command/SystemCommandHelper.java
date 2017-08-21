package com.bits.kghosh.tagit.command;

import com.bits.kghosh.tagit.R;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandInfo;
import com.bits.kghosh.tagit.model.CommandTypeEnum;
import com.bits.kghosh.tagit.model.CommandsEnum;
import com.bits.kghosh.tagit.model.SubCommand;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public class SystemCommandHelper {

    private Map<CommandsEnum, Command> tasksMap = new LinkedHashMap<>();
    private Map<CommandsEnum, Command> actionsMap = new LinkedHashMap<>();

    public SystemCommandHelper() {
        initialize();
    }

    public List<Command> getAllSystemCommands() {
        List<Command> commands = new ArrayList<>(tasksMap.values());
        commands.addAll(new ArrayList<>(actionsMap.values()));
        return commands;
    }

    public List<Command> getAllSystemCommands(CommandTypeEnum type) {
        switch (type) {
            case ACTION:
                return new ArrayList<>(actionsMap.values());
            case TASK:
            default:
                return new ArrayList<>(tasksMap.values());
        }
    }

    public Command getSystemCommand(CommandsEnum commandType) {
        Command command = tasksMap.get(commandType);
        if (command == null) {
            command = actionsMap.get(commandType);
        }
        return command;
    }

    public static int getCommandImage(CommandsEnum commandType) {
        switch (commandType) {
            case AIRPLANE_MODE:
                return R.mipmap.airplane;
            case BATTERY_SAVER:
                return R.mipmap.battery;
            case BLUETOOTH:
                return R.mipmap.bluetooth;
            case BRIGHTNESS:
                return R.mipmap.brightness;
            case BUSINESS_CARD:
                return R.mipmap.business_card;
            case EMAIL:
                return R.mipmap.email;
            case GEOLOCATION:
                return R.mipmap.location;
            case GOOGLE_PLAY:
                return R.mipmap.google_play;
            case LAUNCH_APPLICATION:
                return R.mipmap.application;
            case LAUNCH_MUSIC_PLAYER:
                return R.mipmap.music_player;
            case LINK:
                return R.mipmap.link;
            case MOBILE_DATA:
                return R.mipmap.data;
            case PLAIN_TEXT:
                return R.mipmap.plain_text;
            case SMS:
                return R.mipmap.sms;
            case SYSTEM_VOLUME:
                return R.mipmap.system_volume;
            case WIFI:
                return R.mipmap.wifi;
            default:
                return R.mipmap.application;
        }
    }

    private void initialize() {
        tasksMap.put(CommandsEnum.AIRPLANE_MODE, getAirplanceCommand());
        tasksMap.put(CommandsEnum.BATTERY_SAVER, getBatterySaverCommand());
        tasksMap.put(CommandsEnum.BLUETOOTH, getBluetoothCommand());
        tasksMap.put(CommandsEnum.BRIGHTNESS, getBrightnessCommand());
        tasksMap.put(CommandsEnum.LAUNCH_APPLICATION, getLaunchApplicationCommand());
        tasksMap.put(CommandsEnum.LAUNCH_MUSIC_PLAYER, getLaunchMusicCommand());
        tasksMap.put(CommandsEnum.MOBILE_DATA, getMobileDataCommand());
        tasksMap.put(CommandsEnum.SYSTEM_VOLUME, getSystemVolumeCommand());
        tasksMap.put(CommandsEnum.WIFI, getWifiCommand());

        actionsMap.put(CommandsEnum.BUSINESS_CARD, getBusinessCardCommand());
        actionsMap.put(CommandsEnum.EMAIL, getEmailCommand());
        actionsMap.put(CommandsEnum.GEOLOCATION, getGeolocationCommand());
        actionsMap.put(CommandsEnum.GOOGLE_PLAY, getGooglePlayLaunchCommand());
        actionsMap.put(CommandsEnum.SMS, getSMSCommand());
        actionsMap.put(CommandsEnum.PLAIN_TEXT, getPlainTextCommand());
        actionsMap.put(CommandsEnum.LINK, getLinkCommand());
    }

    private Command getAirplanceCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Airplane Mode");
        commandInfo.setCommand(CommandsEnum.AIRPLANE_MODE);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

    private Command getBluetoothCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Bluetooth");
        commandInfo.setCommand(CommandsEnum.BLUETOOTH);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

    private Command getBrightnessCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Brightness");
        commandInfo.setCommand(CommandsEnum.BRIGHTNESS);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

    private Command getBusinessCardCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Contact");
        commandInfo.setCommand(CommandsEnum.BUSINESS_CARD);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.ACTION);
        return command;
    }

    private Command getEmailCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Email");
        commandInfo.setCommand(CommandsEnum.EMAIL);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.ACTION);
        return command;
    }

    private Command getGeolocationCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Location");
        commandInfo.setCommand(CommandsEnum.GEOLOCATION);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.ACTION);
        return command;
    }

    private Command getGooglePlayLaunchCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Google Play");
        commandInfo.setCommand(CommandsEnum.GOOGLE_PLAY);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.ACTION);
        return command;
    }

    private Command getLaunchApplicationCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Launch Application");
        commandInfo.setCommand(CommandsEnum.LAUNCH_APPLICATION);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

    private Command getLaunchMusicCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Launch Music Player");
        commandInfo.setCommand(CommandsEnum.LAUNCH_MUSIC_PLAYER);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

    private Command getLinkCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Website URL");
        commandInfo.setCommand(CommandsEnum.LINK);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.ACTION);
        return command;
    }

    private Command getPlainTextCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Text");
        commandInfo.setCommand(CommandsEnum.PLAIN_TEXT);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.ACTION);
        return command;
    }

    private Command getSMSCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("SMS");
        commandInfo.setCommand(CommandsEnum.SMS);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.ACTION);
        return command;
    }

    private Command getSystemVolumeCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("System Volume");
        commandInfo.setCommand(CommandsEnum.SYSTEM_VOLUME);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

    private Command getWifiCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Wifi");
        commandInfo.setCommand(CommandsEnum.WIFI);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

    private Command getMobileDataCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Mobile Data");
        commandInfo.setCommand(CommandsEnum.MOBILE_DATA);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

    private Command getBatterySaverCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Battery Saver");
        commandInfo.setCommand(CommandsEnum.BATTERY_SAVER);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setSubCommands(new ArrayList<SubCommand>());
        command.setType(CommandTypeEnum.TASK);
        return command;
    }

}
