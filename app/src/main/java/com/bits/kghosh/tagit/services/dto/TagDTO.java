package com.bits.kghosh.tagit.services.dto;

import com.bits.kghosh.tagit.model.Tag;

/**
 * Created by kghosh on 05-Aug-2017.
 */

public class TagDTO {
    private static final TagDTO ourInstance = new TagDTO();
    private Tag tag;

    public static TagDTO getInstance() {
        return ourInstance;
    }

    private TagDTO() {
    }

    public Tag getTagTransfered() {
        return tag;
    }

    public void setTagToTransfer(Tag tag) {
        this.tag = tag;
    }
}
