package com.bits.kghosh.tagit.command;

import android.content.Context;

import com.bits.kghosh.tagit.command.impl.action.ContactCommandService;
import com.bits.kghosh.tagit.command.impl.action.URLCommandService;
import com.bits.kghosh.tagit.command.impl.task.BluetothModeCommandService;
import com.bits.kghosh.tagit.command.impl.task.BrightnessModeCommandService;
import com.bits.kghosh.tagit.model.CommandsEnum;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public class CommandServiceFactory {

    private Context context;

    public CommandServiceFactory(Context context) {
        this.context = context;
    }

    public CommandService getCommandService(CommandsEnum commandType) {
        switch (commandType) {
            case AIRPLANE_MODE:
            case BATTERY_SAVER:
            case BLUETOOTH:
                return new BluetothModeCommandService(context);
            case BRIGHTNESS:
                return new BrightnessModeCommandService(context);
            case BUSINESS_CARD:
                return new ContactCommandService(context);
            case EMAIL:
            case GEOLOCATION:
            case GOOGLE_PLAY:
            case LAUNCH_APPLICATION:
            case LAUNCH_MUSIC_PLAYER:
            case LINK:
                return new URLCommandService(context);
            case MOBILE_DATA:
            case PLAIN_TEXT:
            case SMS:
            case SYSTEM_VOLUME:
            case WIFI:
            default:
                return null;
        }

    }
}
