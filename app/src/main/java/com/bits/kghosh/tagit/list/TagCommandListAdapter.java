package com.bits.kghosh.tagit.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bits.kghosh.tagit.R;
import com.bits.kghosh.tagit.model.Command;
import com.bits.kghosh.tagit.model.CommandsEnum;

import java.util.List;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class TagCommandListAdapter extends RecyclerView.Adapter<TagCommandListAdapter.MyViewHolder> {

    private List<Command> itemList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.task_title);
            description = (TextView) view.findViewById(R.id.task_desc);
            icon = (ImageView) view.findViewById(R.id.task_icon);
        }
    }


    public TagCommandListAdapter(Context ctx, List<Command> moviesList) {
        this.context = ctx;
        this.itemList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_task, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Command configItem = itemList.get(position);
        holder.title.setText(configItem.getCommandInfo().getName());
        holder.description.setText(configItem.getCommandInfo().getDescription());
        holder.icon.setBackgroundResource(getImage(configItem.getCommandInfo().getCommand()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private int getImage(CommandsEnum commandType) {
        switch (commandType) {
            case AIRPLANE_MODE:
                return R.mipmap.airplane;
            case BLUETOOTH:
                return R.mipmap.bluetooth;
            case BRIGHTNESS:
                return R.mipmap.brightness;
            case BUSINESS_CARD:
                return R.mipmap.business_card;
            case EMAIL:
                return R.mipmap.email;
            case GEOLOCATION:
                return R.mipmap.location;
            case GOOGLE_PLAY:
                return R.mipmap.google_play;
            case LAUNCH_APPLICATION:
                return R.mipmap.application;
            case LAUNCH_MUSIC_PLAYER:
                return R.mipmap.music_player;
            case LINK:
                return R.mipmap.link;
            case PLAIN_TEXT:
                return R.mipmap.plain_text;
            case SMS:
                return R.mipmap.sms;
            case SYSTEM_VOLUME:
                return R.mipmap.system_volume;
            case WIFI:
                return R.mipmap.wifi;
            default:
                return R.mipmap.application;
        }
    }
}
