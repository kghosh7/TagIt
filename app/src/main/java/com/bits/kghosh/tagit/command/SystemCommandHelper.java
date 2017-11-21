package com.bits.kghosh.tagit.command;

import android.content.Context;

import com.bits.kghosh.tagit.R;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandBehaviorEnum;
import com.bits.kghosh.tagit.model.CommandInfo;
import com.bits.kghosh.tagit.model.CommandsEnum;

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

    private CommandServiceFactory serviceFactory;
    private Context context;

    public SystemCommandHelper(Context context) {
        this.context = context;
        this.serviceFactory = new CommandServiceFactory(context);
        initialize();
    }

    public List<Command> getAllSystemCommands() {
        List<Command> commands = new ArrayList<>(tasksMap.values());
        commands.addAll(new ArrayList<>(actionsMap.values()));
        return commands;
    }

    public List<Command> getAllSystemCommands(CommandBehaviorEnum type) {
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
        return this.buildCommand(
                "Airplane Mode",
                CommandsEnum.AIRPLANE_MODE,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command getBluetoothCommand() {
        return this.buildCommand(
                "Bluetooth",
                CommandsEnum.BLUETOOTH,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command getBrightnessCommand() {
        return this.buildCommand(
                "Brightness",
                CommandsEnum.BRIGHTNESS,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command getBusinessCardCommand() {
        return this.buildCommand(
                "Contact",
                CommandsEnum.BUSINESS_CARD,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.ACTION);
    }

    private Command getEmailCommand() {
        return this.buildCommand(
                "Email",
                CommandsEnum.EMAIL,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.ACTION);
    }

    private Command getGeolocationCommand() {
        return this.buildCommand(
                "Location",
                CommandsEnum.GEOLOCATION,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.ACTION);
    }

    private Command getGooglePlayLaunchCommand() {
        return this.buildCommand(
                "Google Play",
                CommandsEnum.GOOGLE_PLAY,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.ACTION);
    }

    private Command getLaunchApplicationCommand() {
        return this.buildCommand(
                "Launch Application",
                CommandsEnum.LAUNCH_APPLICATION,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command getLaunchMusicCommand() {
        return this.buildCommand(
                "Launch Music Player",
                CommandsEnum.LAUNCH_MUSIC_PLAYER,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command getLinkCommand() {
        return this.buildCommand(
                "Website URL",
                CommandsEnum.LINK,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.ACTION);
    }

    private Command getPlainTextCommand() {
        return this.buildCommand(
                "Text",
                CommandsEnum.PLAIN_TEXT,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.ACTION);
    }

    private Command getSMSCommand() {
        return this.buildCommand(
                "SMS",
                CommandsEnum.SMS,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.ACTION);
    }

    private Command getSystemVolumeCommand() {
        return this.buildCommand(
                "System Volume",
                CommandsEnum.SYSTEM_VOLUME,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command getWifiCommand() {
        return this.buildCommand(
                "Wifi",
                CommandsEnum.WIFI,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command getMobileDataCommand() {
        return this.buildCommand(
                "Mobile Data",
                CommandsEnum.MOBILE_DATA,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command getBatterySaverCommand() {
        return this.buildCommand(
                "Battery Saver",
                CommandsEnum.BATTERY_SAVER,
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.",
                CommandBehaviorEnum.TASK);
    }

    private Command buildCommand(String commandName, CommandsEnum commandType,
                                 String commandDescription, CommandBehaviorEnum commandBehavior) {
        Command command = new Command();

        command.setBehavior(commandBehavior);

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName(commandName);
        commandInfo.setCommand(commandType);
        commandInfo.setDescription(commandDescription);
        command.setCommandInfo(commandInfo);

        final CommandService commandService = this.serviceFactory.getCommandService(commandType);
        if (commandService != null) {
            command.setSubCommands(commandService.getSubCommands());
        }

        return command;
    }

}
