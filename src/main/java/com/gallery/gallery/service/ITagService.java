package com.gallery.gallery.service;

import com.gallery.gallery.entity.Tag;

import java.util.List;

public interface ITagService {

    List<Tag> getAllTags();
    Tag getTagById(Long tagId);
    void deleteTag(Long tagId);
    Tag saveTag(Tag tag);

}
