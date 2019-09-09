package com.gallery.gallery.service;


import com.gallery.gallery.DAO.ITagRepository;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private ITagRepository ITagRepository;

    public List<Tag> getAllTags() {
        return ITagRepository.findAll();
    }

    public Tag getTagById(Long tagId) {
        return ITagRepository.findById(tagId)
                .orElseThrow(() -> new MyFileNotFoundException("Tag not found with id " + tagId));
    }

    public List<Tag> getTagsByName(String tagName) {
        return ITagRepository.findAllByName(tagName);
    }

    public void deleteTag(Long tagId) {
        ITagRepository.deleteById(tagId)
               /* .orElseThrow(() -> new MyFileNotFoundException("Tag not found with id " + tagId) {
                })*/
                ;
    }



    public Tag saveTag(Tag tag) {
        return ITagRepository.save(tag);
    }

}
