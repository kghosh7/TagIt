package com.bits.kghosh.tagit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.bits.kghosh.tagit.list.CommandListAdapter;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.Tag;
import com.bits.kghosh.tagit.notifications.InAppNotifications;
import com.bits.kghosh.tagit.services.dto.TagDTO;

import java.util.ArrayList;
import java.util.List;

public class ConfigureTagActivity extends AppCompatActivity {

    private List<Command> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CommandListAdapter mAdapter;
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

        TagDTO tagDTO = TagDTO.getInstance();
        tag = tagDTO.getTagTransfered();
        getSupportActionBar().setTitle("Configure '" + tag.getName() + "'");

        initClickHandlers();
    }

    private void initClickHandlers() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_configure_tag);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_config_tag_add:
                                Intent commandListIntent = new Intent(ConfigureTagActivity.this, CommandListActivity.class);
                                startActivity(commandListIntent);
                                break;
                            case R.id.navigation_config_tag_save:
                                InAppNotifications.showSuccessMiddle(ConfigureTagActivity.this, masterView, "Configuraton saved successfully");
                                break;
                            case R.id.navigation_config_tag_write:
                                InAppNotifications.showErrorMiddle(ConfigureTagActivity.this, masterView, "No NFC tag found to write to");
                                break;
                        }
                        return true;
                    }
                });
    }
}
