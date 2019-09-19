package com.gallery.gallery.service.implementations;


import com.gallery.gallery.DAO.ITagRep;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.exceptions.NotFoundException;
import com.gallery.gallery.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService implements ITagService {

    @Autowired
    private ITagRep tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new NotFoundException("Tag not found with id " + tagId));
    }

    public void deleteTag(Long tagId) {
        tagRepository.deleteById(tagId);
    }

    public Tag saveTag(String name) {
        if (name != null){
            Tag tag = new Tag();
            tag.setName(name);
            return tagRepository.save(tag);
        } else {
            return null;
        }
    }

}
