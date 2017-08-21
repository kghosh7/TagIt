package com.bits.kghosh.tagit;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bits.kghosh.tagit.command.SystemCommandHelper;
import com.bits.kghosh.tagit.list.CommandListAdapter;
import com.bits.kghosh.tagit.list.listeners.RecyclerItemClickListener;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandTypeEnum;
import com.bits.kghosh.tagit.services.dto.CommandDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommandListAdapter mAdapter;
    private CommandTypeEnum commandType = CommandTypeEnum.TASK;
    private List<String> exclusionsCommands = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();
        initializeClickHandlers();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getPageTitle(this.commandType));

        mAdapter = new CommandListAdapter(this.getCommandsToShow());
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
            this.commandType = CommandTypeEnum.valueOf(type);
        } catch (IllegalArgumentException iae) {
            this.commandType = CommandTypeEnum.TASK;
        }

        final String[] exclusionses = getIntent().getStringArrayExtra("EXCLUSIONS");
        if (exclusionses != null) {
            this.exclusionsCommands = Arrays.asList(exclusionses);
        }
    }

    private void initializeClickHandlers() {
        recyclerView = (RecyclerView) findViewById(R.id.commandListView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CommandListActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Command item = mAdapter.getItem(position);
                CommandDTO commandDTO = CommandDTO.getInstance();
                commandDTO.setCommandToTransfer(item);

/*                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);*/
                setResult(Activity.RESULT_OK);
                finish();
            }
        }));
    }

    private List<Command> getCommandsToShow() {
        SystemCommandHelper commandHelper = new SystemCommandHelper();
        List<Command> commands = commandHelper.getAllSystemCommands(this.commandType);

        List<Command> commandsToShow = new ArrayList<>();

        if (exclusionsCommands != null && exclusionsCommands.size() > 0) {
            for (int i = 0; i < commands.size(); i++) {
                if (!exclusionsCommands.contains(commands.get(i).getCommandInfo().getCommand().name())) {
                    commandsToShow.add(commands.get(i));
                }
            }
        } else {
            commandsToShow = commands;
        }

        return commandsToShow;
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
