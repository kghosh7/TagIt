package com.bits.kghosh.tagit.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class Tag {
    private final long id;
    private String name;
    private String description;
    private List<Command> commands;
    private long createdAt;
    private long updatedAt;

    private boolean hasTasks = false;
    private boolean hasAction = false;

    public Tag() {
        this.id = System.currentTimeMillis();
        this.commands = new ArrayList<>();
    }

    public Tag(String name) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.commands = new ArrayList<>();
    }

    private void checkForTaskAndActionExistence() {
        for (int i = 0; i < this.commands.size(); i++) {
            if (this.commands.get(i).getBehavior() == CommandBehaviorEnum.TASK) {
                this.hasTasks = true;
                break;
            }
        }
        for (int i = 0; i < this.commands.size(); i++) {
            if (this.commands.get(i).getBehavior() == CommandBehaviorEnum.ACTION) {
                this.hasAction = true;
                break;
            }
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
        if (command.getBehavior() == CommandBehaviorEnum.TASK) {
            this.hasTasks = true;
        }
        if (command.getBehavior() == CommandBehaviorEnum.ACTION) {
            this.hasAction = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
        this.checkForTaskAndActionExistence();
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isHasTasks() {
        return hasTasks;
    }

    public boolean isHasAction() {
        return hasAction;
    }
}
