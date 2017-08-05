package com.bits.kghosh.tagit.command;

import com.bits.kghosh.tagit.model.CommandsEnum;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public class CommandServiceFactory {
    public CommandService getCommandService(CommandsEnum commandType) {

        switch (commandType) {
            case AIRPLANE_MODE:
            case BLUETOOTH:
            case BRIGHTNESS:
            case BUSINESS_CARD:
            case EMAIL:
            case GEOLOCATION:
            case LAUNCH_APPLICATION:
            case LAUNCH_MUSIC_PLAYER:
            case LINK:
            case PLAIN_TEXT:
            case SMS:
            case SYSTEM_VOLUME:
            case WIFI:
            default:
                return null;
        }

    }
}
