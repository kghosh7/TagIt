package com.bits.kghosh.tagit.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bits.kghosh.tagit.R;
import com.bits.kghosh.tagit.command.SystemCommandHelper;
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


    public TagCommandListAdapter(Context ctx, List<Command> itemList) {
        this.context = ctx;
        this.itemList = itemList;
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
        return SystemCommandHelper.getCommandImage(commandType);
    }
}
