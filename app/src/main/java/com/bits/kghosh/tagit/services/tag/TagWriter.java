package com.bits.kghosh.tagit.services.tag;

import android.content.Context;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;

import com.bits.kghosh.tagit.command.CommandServiceFactory;
import com.bits.kghosh.tagit.exceptions.InvalidTagDataException;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandsEnum;
import com.bits.kghosh.tagit.model.NdefRecordTypeEnum;
import com.bits.kghosh.tagit.model.SubCommand;
import com.bits.kghosh.tagit.model.Tag;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class TagWriter {

    private CommandServiceFactory factory;
    private Context context;

    public TagWriter(Context context) {
        this.context = context;
        this.factory = new CommandServiceFactory(context);
    }

    public boolean write(Ndef ndef, Tag tag) throws InvalidTagDataException {
        if (tag == null || tag.getCommands() == null || tag.getCommands().size() < 1) {
            throw new InvalidTagDataException("No data to write");
        }

        try {
            if (tag.isHasTasks()) {
                return writeTasks(ndef, tag.getCommands());
            } else if (tag.isHasAction()) {
                return writeAction(ndef, tag.getCommands());
            } else {
                throw new InvalidTagDataException("No data to write");
            }
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean writeTasks(Ndef ndef, List<Command> commands) throws IOException, FormatException {
        boolean success = false;
        if (commands != null && commands.size() > 0) {
            List<String> dataToWrite = new ArrayList<>();
            String subCommandData = "";
            Command command;
            List<SubCommand> subCommands;
            SubCommand subCommand;

            for (int i = 0; i < commands.size(); i++) {
                command = commands.get(i);
                if (command != null) {
                    subCommands = command.getSubCommands();
                    if (subCommands != null && subCommands.size() > 0) {
                        subCommand = subCommands.get(i);
                        if (subCommand != null) {
                            subCommandData = subCommand.getKey() != null && subCommand.getValue() != null ?
                                    subCommand.getKey() + ":" + subCommand.getValue().toString() : "";
                            if (subCommandData != null && subCommandData != "") {
                                dataToWrite.add(subCommandData);
                            }
                            // e.g. ULUL:abc.com;TLTL:9203847564
                        }
                    }
                }
            }
            if (dataToWrite.size() > 0) {
                success = write(ndef, NdefRecordTypeEnum.TAGIT, dataToWrite);
            }
        }
        return success;
    }

    private boolean writeAction(Ndef ndef, List<Command> commands) throws IOException, FormatException {
        boolean success = false;
        Command command = commands.get(0);
        if (command != null) {
            final Object content = this.getActionDataToWrite(command);
            final CommandsEnum commandType = command.getCommandInfo().getCommand();
            final NdefRecordTypeEnum recordType = this.getNdefRecordType(commandType);

            success = write(ndef, recordType, content);
        }
        return success;
    }

    private boolean write(Ndef ndef, NdefRecordTypeEnum type, Object data) throws IOException, FormatException {
        switch (type) {
            case TELEPHONE:
                NdefRecord telephoneRecord = createTelephoneNdefRecord(data);
                return writeToNfc(ndef, telephoneRecord);
            case URI:
                NdefRecord uriRecord = createURINdefRecord(data);
                return writeToNfc(ndef, uriRecord);
            case TAGIT:
                List<NdefRecord> tagItRecords = createTagItRecord(data);
                return writeToNfc(ndef, tagItRecords);
            case TEXT:
            default:
                NdefRecord textRecord = createTextNdefRecord(data);
                return writeToNfc(ndef, textRecord);
        }
    }

    private boolean writeToNfc(Ndef ndef, NdefRecord record) throws IOException, FormatException {
        if (ndef != null && record != null) {
            ndef.connect();
            ndef.writeNdefMessage(new NdefMessage(record));
            ndef.close();
            return true;
        } else {
            return false;
        }
    }

    private boolean writeToNfc(Ndef ndef, List<NdefRecord> records) throws IOException, FormatException {
        if (ndef != null && records != null && records.size() > 0) {
            ndef.connect();
            ndef.writeNdefMessage(new NdefMessage(records.toArray(new NdefRecord[0])));
            ndef.close();
            return true;
        } else {
            return false;
        }
    }

    private NdefRecord createURINdefRecord(Object data) {
        if (data != null && data instanceof String) {
            return NdefRecord.createUri(data.toString());
        } else {
            return null;
        }
    }

    private List<NdefRecord> createTagItRecord(Object data) {
        if (data != null && data instanceof List) {
            if (((List) data).size() > 0 && ((List) data).get(0) instanceof String) {
                List<NdefRecord> records = new ArrayList<>();
                for (int i = 0; i < ((List) data).size(); i++) {
                    records.add(NdefRecord.createExternal(
                            "com.bits.kghosh.tagit",
                            "data",
                            ((List) data).get(i).toString().getBytes())
                    );
                }
                return records;
            }
        }
        return null;
    }

    private NdefRecord createTextNdefRecord(Object data) {
        if (data != null && data instanceof String) {
            return NdefRecord.createMime("text/plain", data.toString().getBytes(Charset.forName("US-ASCII")));
        } else {
            return null;
        }
    }

    private NdefRecord createTelephoneNdefRecord(Object data) {
        if (data != null && data instanceof String) {
            return NdefRecord.createUri("tel:" + data.toString());
        } else {
            return null;
        }
    }

    private NdefRecordTypeEnum getNdefRecordType(CommandsEnum commandType) {
        switch (commandType) {
            case BUSINESS_CARD:
                return NdefRecordTypeEnum.TELEPHONE;
            case LINK:
                return NdefRecordTypeEnum.URI;
            case AIRPLANE_MODE:
            case BATTERY_SAVER:
            case BLUETOOTH:
            case BRIGHTNESS:
            case EMAIL:
            case GEOLOCATION:
            case GOOGLE_PLAY:
            case LAUNCH_APPLICATION:
            case LAUNCH_MUSIC_PLAYER:
            case MOBILE_DATA:
            case PLAIN_TEXT:
            case SMS:
            case SYSTEM_VOLUME:
            case WIFI:
            default:
                return NdefRecordTypeEnum.TEXT;
        }
    }

    private Object getActionDataToWrite(Command command) {
        if (command != null) {
            List<SubCommand> subCommands = command.getSubCommands();
            if (subCommands != null && subCommands.size() > 0) {
                SubCommand comm = subCommands.get(0);
                return comm.getValue();
            }
        }
        return null;
    }
}
