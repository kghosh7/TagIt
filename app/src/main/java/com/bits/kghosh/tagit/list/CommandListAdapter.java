package com.bits.kghosh.tagit.list;

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

public class CommandListAdapter extends RecyclerView.Adapter<CommandListAdapter.MyViewHolder> {

    private List<Command> itemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.system_command_title);
            description = (TextView) view.findViewById(R.id.system_command_desc);
            icon = (ImageView) view.findViewById(R.id.system_command_icon);
        }
    }


    public CommandListAdapter(List<Command> moviesList) {
        this.itemList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_command, parent, false);

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

    public Command getItem(int position) {
        return itemList.get(position);
    }

    private int getImage(CommandsEnum commandType) {
        return SystemCommandHelper.getCommandImage(commandType);
    }
}
