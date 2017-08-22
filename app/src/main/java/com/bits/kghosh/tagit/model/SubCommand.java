package com.bits.kghosh.tagit.model;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public class SubCommand {
    private String key;
    private Object value;
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
