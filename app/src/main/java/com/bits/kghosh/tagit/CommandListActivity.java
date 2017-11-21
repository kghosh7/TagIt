package com.bits.kghosh.tagit;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.bits.kghosh.tagit.command.SystemCommandHelper;
import com.bits.kghosh.tagit.fragments.UrlFragment;
import com.bits.kghosh.tagit.list.CommandListAdapter;
import com.bits.kghosh.tagit.list.listeners.RecyclerItemClickListener;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandBehaviorEnum;
import com.bits.kghosh.tagit.services.dto.CommandDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandListActivity extends AppCompatActivity {

    private static class RequestCode {
        static int PICK_CONTACT = 0;
        static int URL_LINK = 1;
    }

    private RecyclerView recyclerView;
    private CommandListAdapter mAdapter;
    private CommandBehaviorEnum commandType = CommandBehaviorEnum.TASK;
    private List<String> exclusionsCommands = new ArrayList<>();

    private Command clickedItem = null;
    private ViewPager viewPager;

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
            this.commandType = CommandBehaviorEnum.valueOf(type);
        } catch (IllegalArgumentException iae) {
            this.commandType = CommandBehaviorEnum.TASK;
        }

        final String[] exclusions = getIntent().getStringArrayExtra("EXCLUSIONS");
        if (exclusions != null) {
            this.exclusionsCommands = Arrays.asList(exclusions);
        }
    }

    private void initializeClickHandlers() {
        recyclerView = (RecyclerView) findViewById(R.id.commandListView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(CommandListActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                clickedItem = mAdapter.getItem(position);
                takeCommandSubcommandAction();
            }
        }));
    }

    private void takeCommandSubcommandAction() {

        if (clickedItem != null) {
            switch (clickedItem.getCommandInfo().getCommand()) {
                case BUSINESS_CARD:
                    Intent intentContact = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                    startActivityForResult(intentContact, RequestCode.PICK_CONTACT);
                    break;
                case LINK:
//                    Intent intentLink = new Intent(CommandListActivity.this, UrlFragment.class);
//                    Fragment urlFragment = getFragmentManager().findFragmentById(R.layout.fragment_url);
//                    startActivityFromFragment(urlFragment, intentLink, RequestCode.URL_LINK);
                    DialogFragment dialogFragment = new UrlFragment();
                    //dialogFragment.getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    dialogFragment.show(getSupportFragmentManager(), "Dialog");
                    break;
                default:
                    CommandDTO commandDTO = CommandDTO.getInstance();
                    commandDTO.setCommandToTransfer(clickedItem);
                    setResult(Activity.RESULT_OK);
                    finish();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == RequestCode.PICK_CONTACT) {
            String phoneNo = null;

            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor.moveToFirst()) {
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                phoneNo = cursor.getString(phoneIndex);
                clickedItem.getSubCommands().get(0).setValue(phoneNo);
                CommandDTO commandDTO = CommandDTO.getInstance();
                commandDTO.setCommandToTransfer(clickedItem);
            }
            cursor.close();

            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    private List<Command> getCommandsToShow() {
        SystemCommandHelper commandHelper = new SystemCommandHelper(getApplicationContext());
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

    private String getPageTitle(CommandBehaviorEnum commandType) {
        switch (commandType) {
            case ACTION:
                return "Pick an action";
            case TASK:
            default:
                return "Pick a task";
        }
    }
}
