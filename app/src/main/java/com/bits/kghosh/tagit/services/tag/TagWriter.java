package com.bits.kghosh.tagit.services.tag;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;

import com.bits.kghosh.tagit.command.CommandService;
import com.bits.kghosh.tagit.command.CommandServiceFactory;
import com.bits.kghosh.tagit.exceptions.InvalidTagDataException;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandsEnum;
import com.bits.kghosh.tagit.model.NdefRecordTypeEnum;
import com.bits.kghosh.tagit.model.Tag;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class TagWriter {

    CommandServiceFactory factory;

    public TagWriter() {
        this.factory = new CommandServiceFactory();
    }

    public void write(Ndef ndef, Tag tag) throws InvalidTagDataException, IOException, FormatException {
        if (tag == null || tag.getCommands() == null || tag.getCommands().size() < 1) {
            throw new InvalidTagDataException("No data to write");
        }

        if (tag.isHasTasks()) {
            writeTasks(tag.getCommands());
        } else if (tag.isHasAction()) {
            writeAction(ndef, tag.getCommands());
        }
    }

    private void writeTasks(List<Command> commands) throws IOException, FormatException {

    }

    private void writeAction(Ndef ndef, List<Command> commands) throws IOException, FormatException {
        Command command = commands.get(0);
        if (command != null) {
            final CommandsEnum commandType = command.getCommandInfo().getCommand();

            final CommandService commandService = factory.getCommandService(command.getCommandInfo().getCommand());
            if (commandService != null) {
                final Object content = commandService.getDataToWrite(command);
                final NdefRecordTypeEnum recordType = commandService.getRecordType();
                //logic to write
                write(ndef, recordType, content);
            }
        }
    }

    private void write(Ndef ndef, NdefRecordTypeEnum type, Object data) throws IOException, FormatException {
        NdefRecord record = null;
        switch (type) {
            case APPLICATION:
                break;
            case URI:
                record = createURINdefRecord(data);
                break;
            case TEXT:
            default:
                record = createTextNdefRecord(data);
                break;
        }

        if (record != null) {
            writeToNfc(ndef, record);
        }
    }

    private NdefRecord createURINdefRecord(Object data) {
        if (data instanceof String) {
            return NdefRecord.createUri(data.toString());
        } else {
            return null;
        }
    }

    private NdefRecord createTextNdefRecord(Object data) {
        if (data instanceof String) {
            return NdefRecord.createMime("text/plain", data.toString().getBytes(Charset.forName("US-ASCII")));
        } else {
            return null;
        }
    }

    private void writeToNfc(Ndef ndef, NdefRecord record) throws IOException, FormatException {
        if (ndef != null) {
            ndef.connect();
            ndef.writeNdefMessage(new NdefMessage(record));
            ndef.close();
        }
    }

}
