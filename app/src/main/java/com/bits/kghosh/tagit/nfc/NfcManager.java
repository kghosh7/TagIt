package com.bits.kghosh.tagit.nfc;

import android.app.Activity;
import android.nfc.NfcAdapter;

/**
 * Created by kghosh on 21-Aug-2017.
 */

public class NfcManager {
    private Activity activity;
    private NfcAdapter nfcAdapter;

    public NfcManager(Activity activity) {
        this.activity = activity;
    }
}
