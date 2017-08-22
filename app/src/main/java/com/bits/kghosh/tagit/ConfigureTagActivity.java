package com.bits.kghosh.tagit;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NfcAdapter;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bits.kghosh.tagit.command.SystemCommandHelper;
import com.bits.kghosh.tagit.exceptions.InvalidTagDataException;
import com.bits.kghosh.tagit.list.TagCommandListAdapter;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandTypeEnum;
import com.bits.kghosh.tagit.model.CommandsEnum;
import com.bits.kghosh.tagit.model.Tag;
import com.bits.kghosh.tagit.notifications.InAppNotifications;
import com.bits.kghosh.tagit.services.dto.CommandDTO;
import com.bits.kghosh.tagit.services.dto.TagDTO;
import com.bits.kghosh.tagit.services.tag.TagWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigureTagActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TagCommandListAdapter mAdapter;
    private Tag tag;
    View masterView;

    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_tag);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        masterView = findViewById(R.id.configure_tag_main_view);

        initializeNFC();
        initData();
        initializeList();
        initClickHandlers();
    }

    private void initData() {
        TagDTO tagDTO = TagDTO.getInstance();
        tag = tagDTO.getTagTransfered();

        TextView tagName = (TextView) findViewById(R.id.configure_tag_name);
        TextView tagDesc = (TextView) findViewById(R.id.configure_tag_description);
        TextView tagCreatedAt = (TextView) findViewById(R.id.configure_tag_created_at);
        TextView tagUpdatedAt = (TextView) findViewById(R.id.configure_tag_updated_at);

        if (tag != null) {
            getSupportActionBar().setTitle("Configure '" + tag.getName() + "'");
            tagName.setText(tag.getName());
            tagDesc.setText(tag.getDescription());
            tagCreatedAt.setText(new Date(tag.getCreatedAt()).toString());
            tagUpdatedAt.setText(new Date(tag.getUpdatedAt()).toString());
        } else {
            getSupportActionBar().setTitle("Configure your tag");
            tagName.setText("");
            tagDesc.setText("");
            tagCreatedAt.setText(Long.toString(System.currentTimeMillis()));
            tagUpdatedAt.setText(Long.toString(System.currentTimeMillis()));
        }
    }

    private void initializeNFC() {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null) {
            InAppNotifications.showInfo(this, masterView, "NFC available");
        } else {
            InAppNotifications.showError(this, masterView, "NFC not available");
        }
        if (!nfcAdapter.isEnabled()) {
            InAppNotifications.showError(this, masterView, "NFC not enabled");
        }
    }

    private List<Command> getData() {
        List<Command> tasks = new ArrayList<>();
        SystemCommandHelper helper = new SystemCommandHelper();

        Command airplaneCommand = helper.getSystemCommand(CommandsEnum.AIRPLANE_MODE);
        Command airplaneConfig = new Command();
        airplaneConfig.setCommandInfo(airplaneCommand.getCommandInfo());
        tasks.add(airplaneConfig);

        Command batteryCommand = helper.getSystemCommand(CommandsEnum.BATTERY_SAVER);
        Command batteryConfig = new Command();
        batteryConfig.setCommandInfo(batteryCommand.getCommandInfo());
        tasks.add(batteryConfig);

        Command bluetoothCommand = helper.getSystemCommand(CommandsEnum.BLUETOOTH);
        Command bluetoothConfig = new Command();
        bluetoothConfig.setCommandInfo(bluetoothCommand.getCommandInfo());
        tasks.add(bluetoothConfig);

        Command brightnessCommand = helper.getSystemCommand(CommandsEnum.BRIGHTNESS);
        Command brightnessConfig = new Command();
        brightnessConfig.setCommandInfo(brightnessCommand.getCommandInfo());
        tasks.add(brightnessConfig);

        Command businessCardCommand = helper.getSystemCommand(CommandsEnum.BUSINESS_CARD);
        Command businessCardConfig = new Command();
        businessCardConfig.setCommandInfo(businessCardCommand.getCommandInfo());
        tasks.add(businessCardConfig);

        Command launchAppCommand = helper.getSystemCommand(CommandsEnum.LAUNCH_APPLICATION);
        Command launchAppConfig = new Command();
        launchAppConfig.setCommandInfo(launchAppCommand.getCommandInfo());
        tasks.add(launchAppConfig);

        return tasks;
    }

    private void initializeList() {
        //this.tag.setCommands(getData());
        mAdapter = new TagCommandListAdapter(ConfigureTagActivity.this, this.tag.getCommands());
        recyclerView = (RecyclerView) findViewById(R.id.taskListView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void updateList() {
        mAdapter.notifyDataSetChanged();
    }

    private void initClickHandlers() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_configure_tag);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_config_tag_add_task:
                                Intent taskListIntent = new Intent(ConfigureTagActivity.this, CommandListActivity.class);
                                taskListIntent.putExtra("TYPE", CommandTypeEnum.TASK.name());
                                taskListIntent.putExtra("EXCLUSIONS", getTagCommandCodes());
                                startActivityForResult(taskListIntent, ActivityHelper.ActivityResultCodes.ConfigureTagActivity);
                                break;
                            case R.id.navigation_config_tag_add_action:
                                Intent actionListIntent = new Intent(ConfigureTagActivity.this, CommandListActivity.class);
                                actionListIntent.putExtra("TYPE", CommandTypeEnum.ACTION.name());
                                actionListIntent.putExtra("EXCLUSIONS", getTagCommandCodes());
                                startActivityForResult(actionListIntent, ActivityHelper.ActivityResultCodes.ConfigureTagActivity);
                                break;
                            case R.id.navigation_config_tag_write:
                                InAppNotifications.showErrorMiddle(ConfigureTagActivity.this, masterView, "No NFC tag found to write to");
                                break;
                        }
                        return true;
                    }
                });
    }

    private String[] getTagCommandCodes() {
        List<String> tagCommandCodes = new ArrayList<>();

        List<Command> tagCommands = this.tag.getCommands();
        for (int i = 0; i < tagCommands.size(); i++) {
            tagCommandCodes.add(tagCommands.get(i).getCommandInfo().getCommand().name());
        }
        return (String[]) tagCommandCodes.toArray(new String[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_configure_tag, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityHelper.ActivityResultCodes.ConfigureTagActivity) {
            if (resultCode == Activity.RESULT_OK) {
                CommandDTO commandDTO = CommandDTO.getInstance();
                final Command commandTransfered = commandDTO.getCommandTransfered();

                this.tag.addCommand(commandTransfered);
                updateList();
            }
        }
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
    protected void onNewIntent(Intent intent) {
        android.nfc.Tag nfcTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (nfcTag != null) {
            Ndef ndef = Ndef.get(nfcTag);
            TagWriter writer = new TagWriter();
            try {
                writer.write(ndef, tag);
            } catch (InvalidTagDataException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
        }
    }
}
