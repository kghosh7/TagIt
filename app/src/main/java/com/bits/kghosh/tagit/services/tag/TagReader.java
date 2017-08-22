package com.bits.kghosh.tagit.services.tag;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;

import java.io.IOException;

/**
 * Created by kghosh on 18-Aug-2017.
 */

public class TagReader {

    public static void readFromNFC(Ndef ndef) {

        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
            String message = new String(ndefMessage.getRecords()[0].getPayload());

            ndef.close();

        } catch (IOException | FormatException e) {
            e.printStackTrace();

        }
    }
}
