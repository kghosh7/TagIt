package com.bits.kghosh.tagit;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bits.kghosh.tagit.list.TagListAdapter;
import com.bits.kghosh.tagit.list.listeners.RecyclerItemClickListener;
import com.bits.kghosh.tagit.model.Tag;
import com.bits.kghosh.tagit.notifications.InAppNotifications;
import com.bits.kghosh.tagit.services.dto.TagDTO;
import com.bits.kghosh.tagit.services.tag.TagReader;
import com.bits.kghosh.tagit.services.tag.TagWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TagListAdapter mAdapter;

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeNFC();
        initializeList();
        initializeClickHandlers();
    }

    private void initializeNFC() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        View masterView = findViewById(R.id.homepage);
        if (nfcAdapter != null) {
            InAppNotifications.showInfo(this, masterView, "NFC available");
        } else {
            InAppNotifications.showError(this, masterView, "NFC not available");
        }
        if (!nfcAdapter.isEnabled()) {
            InAppNotifications.showError(this, masterView, "NFC not enabled");
        }
    }

    private void initializeClickHandlers() {
        FloatingActionButton fabAddTag = (FloatingActionButton) findViewById(R.id.fab_add_tag);
        fabAddTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addTagIntent = new Intent(HomeActivity.this, ConfigureTagActivity.class);
                startActivity(addTagIntent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.tagListView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(HomeActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Tag item = mAdapter.getItem(position);
                TagDTO tagDTO = TagDTO.getInstance();
                tagDTO.setTagToTransfer(item);

                Intent addTagIntent = new Intent(HomeActivity.this, ConfigureTagActivity.class);
                startActivity(addTagIntent);
            }
        }));
    }

    private void initializeList() {
        mAdapter = new TagListAdapter(HomeActivity.this, getData());
        recyclerView = (RecyclerView) findViewById(R.id.tagListView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private List<Tag> getData() {
        List<Tag> tags = new ArrayList<>();

        Tag tag1 = new Tag();
        tag1.setName("My Car");
        tag1.setDescription("Maximizes volume, enables vibration, starts Google Maps and navigates to office, starts Music player and plays my favorite playlist");
        tag1.setCreatedAt(new Date().getTime());
        tag1.setUpdatedAt(new Date().getTime());
        tags.add(tag1);

        Tag tag2 = new Tag();
        tag2.setName("Bedtime");
        tag2.setDescription("Puts phone on silent, maximizes alarm volume, turns off data, something else, something else and so on");
        tag2.setCreatedAt(new Date().getTime());
        tag2.setUpdatedAt(new Date().getTime());
        tags.add(tag2);

        Tag tag3 = new Tag();
        tag3.setName("Good Morning");
        tag3.setDescription("All volumes maximum, turns Wifi on, turns Data on");
        tag3.setCreatedAt(new Date().getTime());
        tag3.setUpdatedAt(new Date().getTime());
        tags.add(tag3);

        Tag tag4 = new Tag();
        tag4.setName("Back home");
        tag4.setDescription("All volumes maximum, turns Wifi on, turns Data off, some other task, another task, yet another task");
        tag4.setCreatedAt(new Date().getTime());
        tag4.setUpdatedAt(new Date().getTime());
        tags.add(tag4);

        return tags;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (nfcAdapter != null) {
            IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
            IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
            IntentFilter[] nfcIntentFilters = new IntentFilter[]{tagDetected, ndefDetected, techDetected};

            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

            nfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilters, null);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        android.nfc.Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag != null) {
            Ndef ndef = Ndef.get(tag);
            TagReader.readFromNFC(ndef);
            //TagWriter writer = new TagWriter();
            //writer.writeToNfc(ndef, "hey krish");
        }
    }
}
