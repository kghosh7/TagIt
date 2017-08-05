package com.bits.kghosh.tagit.command;

import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandInfo;
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

    private Map<CommandsEnum, Command> commandsMap = new LinkedHashMap<>();

    public List<Command> getAllSystemCommands() {
        initialize();

        List<Command> commands = new ArrayList<>(commandsMap.values());
        return commands;
    }

    private void initialize() {
        commandsMap.put(CommandsEnum.AIRPLANE_MODE, getAirplanceCommand());
        commandsMap.put(CommandsEnum.BLUETOOTH, getBluetoothCommand());
        commandsMap.put(CommandsEnum.BRIGHTNESS, getBrightnessCommand());
        commandsMap.put(CommandsEnum.BUSINESS_CARD, getBusinessCardCommand());
        commandsMap.put(CommandsEnum.EMAIL, getEmailCommand());
        commandsMap.put(CommandsEnum.GEOLOCATION, getGeolocationCommand());
        commandsMap.put(CommandsEnum.LAUNCH_APPLICATION, getLaunchApplicationCommand());
        commandsMap.put(CommandsEnum.LAUNCH_MUSIC_PLAYER, getLaunchMusicCommand());
        commandsMap.put(CommandsEnum.LINK, getLinkCommand());
        commandsMap.put(CommandsEnum.PLAIN_TEXT, getPlainTextCommand());
        commandsMap.put(CommandsEnum.SMS, getSMSCommand());
        commandsMap.put(CommandsEnum.SYSTEM_VOLUME, getSystemVolumeCommand());
        commandsMap.put(CommandsEnum.WIFI, getWifiCommand());
    }

    private Command getAirplanceCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Airplane Mode");
        commandInfo.setCommand(CommandsEnum.AIRPLANE_MODE);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getBluetoothCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Bluetooth");
        commandInfo.setCommand(CommandsEnum.BLUETOOTH);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getBrightnessCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Brightness");
        commandInfo.setCommand(CommandsEnum.BRIGHTNESS);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getBusinessCardCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Contact");
        commandInfo.setCommand(CommandsEnum.BUSINESS_CARD);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getEmailCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Email");
        commandInfo.setCommand(CommandsEnum.EMAIL);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getGeolocationCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Location");
        commandInfo.setCommand(CommandsEnum.GEOLOCATION);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getLaunchApplicationCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Launch Application");
        commandInfo.setCommand(CommandsEnum.LAUNCH_APPLICATION);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getLaunchMusicCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Launch Music Player");
        commandInfo.setCommand(CommandsEnum.LAUNCH_MUSIC_PLAYER);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getLinkCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Website URL");
        commandInfo.setCommand(CommandsEnum.LINK);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getPlainTextCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Text");
        commandInfo.setCommand(CommandsEnum.PLAIN_TEXT);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getSMSCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("SMS");
        commandInfo.setCommand(CommandsEnum.SMS);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getSystemVolumeCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("System Volume");
        commandInfo.setCommand(CommandsEnum.SYSTEM_VOLUME);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

    private Command getWifiCommand() {
        Command command = new Command();

        CommandInfo commandInfo = new CommandInfo();
        commandInfo.setName("Wifi");
        commandInfo.setCommand(CommandsEnum.WIFI);
        commandInfo.setDescription("Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque.");

        command.setCommandInfo(commandInfo);
        command.setAllSubCommands(new ArrayList<SubCommand>());
        return command;
    }

}
