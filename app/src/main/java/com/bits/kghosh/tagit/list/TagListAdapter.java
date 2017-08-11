package com.bits.kghosh.tagit.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bits.kghosh.tagit.R;
import com.bits.kghosh.tagit.model.Tag;

import java.util.List;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class TagListAdapter extends RecyclerView.Adapter<TagListAdapter.MyViewHolder> {

    private Context context;
    private List<Tag> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, commandCount;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tag_title);
            description = (TextView) view.findViewById(R.id.tag_desc);
            icon = (ImageView) view.findViewById(R.id.tag_icon);
            commandCount = (TextView) view.findViewById(R.id.tag_command_count);
        }
    }

    public TagListAdapter(Context ctx, List<Tag> moviesList) {
        this.context = ctx;
        this.itemList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_tag, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tag tagItem = itemList.get(position);
        holder.title.setText(tagItem.getName());
        holder.description.setText(tagItem.getDescription());
        holder.commandCount.setText("Total commands: " + tagItem.getCommands().size());
        holder.icon.setBackgroundResource(getImage(tagItem.getName()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public Tag getItem(int position) {
        return itemList.get(position);
    }

    private int getImage(String title) {
        if (title.toLowerCase().contains("car")) {
            return R.mipmap.icon_car;
        } else if (title.toLowerCase().contains("home")) {
            return R.mipmap.icon_home;
        } else if (title.toLowerCase().contains("morning")) {
            return R.mipmap.icon_morning;
        } else if (title.toLowerCase().contains("night") || title.toLowerCase().contains("bed")) {
            return R.mipmap.icon_night;
        } else {
            return R.mipmap.icon_tag;
        }
    }
}
