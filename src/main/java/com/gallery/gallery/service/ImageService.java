package com.gallery.gallery.service;

import com.gallery.gallery.DAO.ICategoryRep;
import com.gallery.gallery.DAO.IImageRep;
import com.gallery.gallery.DAO.ITagRepository;
import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Image;
import com.gallery.gallery.entity.ImageFull;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.exceptions.FileStorageException;
import com.gallery.gallery.exceptions.MyFileNotFoundException;
import com.gallery.gallery.payload.ResizedImage;
import com.gallery.gallery.util.ImageResizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ImageService {

    @Autowired
    private IImageRep imageRep;
    @Autowired
    private ITagRepository iTagRepository;
    @Autowired
    private ICategoryRep iCategoryRep;

    public Image storeFile(MultipartFile file, String description) {
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(imageName.contains("..")) {
                throw new MyFileNotFoundException("Filename contains invalid path sequence " + imageName);
            }
            String imageString = imageName.substring(0, imageName.lastIndexOf("."));
//            InputStream in = new ByteArrayInputStream(file.getBytes());

            ResizedImage resizedImage = ImageResizeUtil.resize(file.getBytes());

            ImageFull full = new ImageFull();
            full.setData(file.getBytes());
            Image image = new Image(imageString, file.getContentType(), file.getSize(),
                    resizedImage.getData(), description, resizedImage.getHeight(), resizedImage.getWidth(), full);
            return imageRep.save(image);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + imageName + ". Please try again!", ex);
        }
    }

    public Image getFile(Long fileId) {
        return imageRep.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    public List<Image> getAllFiles() {
        return (List<Image>) imageRep.findAll();
    }

    public void deleteFile(Long fileId) {
        imageRep.deleteById(fileId);
    }

    public List<Image> getAllImagesByCategories(List<Category> categoryList) {

        return imageRep.findAllByCategories(categoryList);
    }

    public List<Image> getFilesByFileName(String fileName) {
        return imageRep.findAllByName(fileName);
    }

    public Image updateImage(Long id, String description, String name,
                             List<Long> categoriesIds, List<Long> tagsIds){
        Optional<Image> optionalImage = imageRep.findById(id);
        Image image = null;
        // exception optionalImage .ifPresent() -> lambda
        if (optionalImage != null){
            image = optionalImage.get();
            Set<Category> categories = (Set<Category>) iCategoryRep.findAllById(categoriesIds);
            Set<Tag> tags = (Set<Tag>) iTagRepository.findAllById(tagsIds);
            if (categories != null){
                image.setCategories(categories);
            }if (tags != null){
                image.setTags(tags);
            }
            if (name != null){
                image.setName(name);
            }
            if (description != null){
                image.setDescription(description);
            }
        }
        return  imageRep.save(image);
    };

    //    public List<Image> findByNameDes(String text) {
//        return imageRep.customFindByNameDes(text);
//    }

    /*public void addCategory(Long imageId, Category[] category) {
        Optional<Image> image = imageRep.findById(imageId);
        image.setCategories(category);
        imageRep.save(image);
    }*/

    /*public List<DBFile> getFileByTagName(String tagName) {
        return fileRep.getFileByTagName(tagName);
        //.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + tagName))
    }*/

    /*public List<DBFile> getFilesByTagName() {
        return dBFileRepository.findByTags();
    }*/


}