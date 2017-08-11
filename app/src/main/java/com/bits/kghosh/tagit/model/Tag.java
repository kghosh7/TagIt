package com.bits.kghosh.tagit.model;

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

    public Tag() {
        this.id = System.currentTimeMillis();
    }

    public Tag(String name) {
        this.id = System.currentTimeMillis();
        this.name = name;
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
}
