package com.gallery.gallery.service.implementations;

import com.gallery.gallery.DAO.ICategoryRep;
import com.gallery.gallery.DAO.IImageRep;
import com.gallery.gallery.DAO.ITagRep;
import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Image;
import com.gallery.gallery.entity.ImageFull;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.exceptions.FileStorageException;
import com.gallery.gallery.exceptions.NotFoundException;
import com.gallery.gallery.payload.ImageUpdate;
import com.gallery.gallery.payload.ImageUpload;
import com.gallery.gallery.payload.ResizedImage;
import com.gallery.gallery.service.IImageService;
import com.gallery.gallery.util.ImageResizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.IOException;
import java.util.*;

@Service
public class ImageService implements IImageService {

    @Autowired
    private EntityManager em;
    @Autowired
    private IImageRep imageRep;
    @Autowired
    private ITagRep tagRepository;
    @Autowired
    private ICategoryRep categoryRep;

    public Image saveImage(ImageUpload imageUpload) {
        MultipartFile file = imageUpload.getFile();
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(imageName.contains("..")) {
                throw new NotFoundException("Filename contains invalid path sequence " + imageName);
            }
            String imageString = imageName.substring(0, imageName.lastIndexOf("."));

            ResizedImage resizedImage = ImageResizeUtil.resize(file.getBytes());

            ImageFull full = new ImageFull();
            full.setData(file.getBytes());
            Image image = new Image(imageString, file.getContentType(), file.getSize(),
                    resizedImage.getData(), imageUpload.getDescription(), resizedImage.getHeight(),
                    resizedImage.getWidth(), full, imageUpload.getCategories(), imageUpload.getTags());
            return imageRep.save(image);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + imageName + ". Please try again!", ex);
        }
    }
    public Image getImage(Long imageId) {
        return imageRep.findById(imageId)
                .orElseThrow(() -> new NotFoundException("File not found with id " + imageId));
    }

    public List<Image> getAllImages() {
        return (List<Image>) imageRep.findAll();
    }

    public void deleteImage(Long imageId) {
        imageRep.deleteById(imageId);
    }

    public Image updateImage( ImageUpdate imageUpdate ) {
        Optional<Image> optionalImage = imageRep.findById(imageUpdate.getId());
        Image image = null;
        // exception optionalImage .ifPresent() -> lambda
        if (optionalImage != null){
            image = optionalImage.get();

            image.setCategories(imageUpdate.getCategories());
            image.setTags(imageUpdate.getTags());
            image.setName(imageUpdate.getName());
            image.setDescription(imageUpdate.getDescription());
            image.setDate(imageUpdate.getDate());
        }
        return  imageRep.save(image);
    };

    public List<Image> customFindByNameDes(String search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        cq.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if(search!=null) {
            predicates.add(cb.or(
                    cb.like(root.get("name"), "%" + search + "%"),
                    cb.like(root.get("description"), "%" + search + "%"))
            );
        }
        return (List<Image>) cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public List<Image> getAllImagesBySearch(String searchString, List<Long> tagsIds, List<Long> categoriesIds) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);

        Join<Image, Tag> tags = root.join("tags", JoinType.LEFT);
        Join<Image, Category> categories = root.join("categories", JoinType.LEFT);
        cq.select(root);

        Predicate predicate = null;
        Predicate search = null;
        Predicate tagsSearch = null;
        Predicate categoriesSearch = null;


        if (!searchString.isEmpty()) {
            Predicate namePredicate = cb.like(root.get("name"), "%" + searchString + "%");
            Predicate descriptionPredicate = cb.like(root.get("description"), "%" + searchString + "%");
            search = cb.or(namePredicate, descriptionPredicate);
        }
        if (tagsIds.size() > 0) {
            tagsSearch = cb.equal(tags.get("id"), tagsIds.get(0));
            if (tagsIds.size() > 1) {
                for (int i = 1; i < tagsIds.size(); i++) {
                    Predicate currentPredicate = cb.equal(tags.get("id"), tagsIds.get(i));
                    tagsSearch = cb.or(tagsSearch, currentPredicate);
                }
            }
        }
        if (categoriesIds.size() > 0) {
            categoriesSearch = cb.equal(categories.get("id"), categoriesIds.get(0));
            if (categoriesIds.size() > 1) {
                for (int i = 1; i < categoriesIds.size(); i++) {
                    Predicate currentPredicate = cb.equal(categories.get("id"), categoriesIds.get(i));
                    categoriesSearch = cb.or(categoriesSearch, currentPredicate);
                }
            }
        }

        if (search != null) {
            predicate = search;
        }

        if (tagsSearch != null) {
            if (predicate != null) {
                predicate = cb.and(predicate, tagsSearch);
            } else {
                predicate = tagsSearch;
            }
        }

        if (categoriesSearch != null) {
            if (predicate != null) {
                predicate = cb.and(predicate, categoriesSearch);
            } else {
                predicate = categoriesSearch;
            }
        }

        if (predicate != null) {

            cq.where(predicate);
            List<Image> filteredImages = new ArrayList<>(
                    new HashSet<>(em.createQuery(cq).getResultList()));
            return filteredImages;
        } else {
            return null;
        }

    }

}