package com.bits.kghosh.tagit.model;

import java.util.List;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class Tag {
    private final long id;
    private String name;
    private String description;
    private List<SystemConfig> configs;

    public Tag() {
        this.id = System.currentTimeMillis();
    }

    public Tag(String name) {
        this.id = System.currentTimeMillis();
        this.name = name;

        SystemConfig config = new SystemConfig("Wifi", "on");
        SystemConfig anotherConfig = new SystemConfig("Playlist", "workout");

        this.configs.add(config);
        this.configs.add(anotherConfig);
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

    public List<SystemConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<SystemConfig> configs) {
        this.configs = configs;
    }
}
