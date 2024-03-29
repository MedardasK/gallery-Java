package com.gallery.gallery.service.implementations;


import com.gallery.gallery.DAO.ITagRep;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.service.ITagService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements ITagService {

    @Autowired
    private ITagRep tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long tagId) {
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if (tagOptional.isPresent()) {
            return tagOptional.get();
        }
        else {
            return null;
        }
    }

    public String deleteTag(Long tagId) {
        try {
            tagRepository.deleteById(tagId);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }

    public Tag saveTag(String name) {
        if (tagRepository.findByName(name.toLowerCase()) == null) {
            Tag tag = new Tag();
            tag.setName(name);
            return tagRepository.save(tag);
        } else {
            return null;
        }
    }

}
