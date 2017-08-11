package com.bits.kghosh.tagit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bits.kghosh.tagit.command.SystemCommandHelper;
import com.bits.kghosh.tagit.list.CommandListAdapter;
import com.bits.kghosh.tagit.model.CommandTypeEnum;

public class CommandListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommandListAdapter mAdapter;
    private CommandTypeEnum commandType = CommandTypeEnum.TASK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getPageTitle(this.commandType));

        SystemCommandHelper commandHelper = new SystemCommandHelper();
        mAdapter = new CommandListAdapter(commandHelper.getAllSystemCommands(this.commandType));
        recyclerView = (RecyclerView) findViewById(R.id.commandListView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void initialize() {
        String type = getIntent().getStringExtra("TYPE");
        try {
            commandType = CommandTypeEnum.valueOf(type);
        } catch (IllegalArgumentException iae) {
            commandType = CommandTypeEnum.TASK;
        }
    }

    private String getPageTitle(CommandTypeEnum commandType) {
        switch (commandType) {
            case ACTION:
                return "Pick an action";
            case TASK:
            default:
                return "Pick a task";
        }
    }
}
