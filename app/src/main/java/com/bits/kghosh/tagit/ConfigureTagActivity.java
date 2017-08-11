package com.bits.kghosh.tagit;

import android.content.Intent;
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
import com.bits.kghosh.tagit.list.TagCommandListAdapter;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandTypeEnum;
import com.bits.kghosh.tagit.model.CommandsEnum;
import com.bits.kghosh.tagit.model.Tag;
import com.bits.kghosh.tagit.notifications.InAppNotifications;
import com.bits.kghosh.tagit.services.dto.TagDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigureTagActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TagCommandListAdapter mAdapter;
    private Tag tag;
    View masterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_tag);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        masterView = findViewById(R.id.configure_tag_main_view);

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
        mAdapter = new TagCommandListAdapter(ConfigureTagActivity.this, getData());
        recyclerView = (RecyclerView) findViewById(R.id.taskListView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
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
                                startActivity(taskListIntent);
                                break;
                            case R.id.navigation_config_tag_add_action:
                                Intent actionListIntent = new Intent(ConfigureTagActivity.this, CommandListActivity.class);
                                actionListIntent.putExtra("TYPE", CommandTypeEnum.ACTION.name());
                                startActivity(actionListIntent);
                                break;
                            case R.id.navigation_config_tag_write:
                                InAppNotifications.showErrorMiddle(ConfigureTagActivity.this, masterView, "No NFC tag found to write to");
                                break;
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_configure_tag, menu);
        return true;
    }
}
