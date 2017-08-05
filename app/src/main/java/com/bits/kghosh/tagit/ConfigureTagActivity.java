package com.bits.kghosh.tagit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bits.kghosh.tagit.command.SystemCommandHelper;
import com.bits.kghosh.tagit.list.CommandListAdapter;
import com.bits.kghosh.tagit.model.Command;

import java.util.ArrayList;
import java.util.List;

public class ConfigureTagActivity extends AppCompatActivity {

    private List<Command> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CommandListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_tag);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SystemCommandHelper commandHelper = new SystemCommandHelper();
        mAdapter = new CommandListAdapter(commandHelper.getAllSystemCommands());
        recyclerView = (RecyclerView) findViewById(R.id.commandListView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();
    }
}
