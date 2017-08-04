package com.bits.kghosh.tagit.model;

/**
 * Created by kghosh on 04-Aug-2017.
 */

public class SystemConfig {
    private String name;
    private String value;

    public SystemConfig(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
