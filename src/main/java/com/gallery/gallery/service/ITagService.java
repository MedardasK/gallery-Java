package com.gallery.gallery.service;

import com.gallery.gallery.entity.Tag;

import java.util.List;

public interface ITagService {

    List<Tag> getAllTags();

    Tag getTagById(Long tagId);

    String deleteTag(Long tagId);

    Tag saveTag(String tag);

}
