package com.gallery.gallery.service.implementations;

import com.gallery.gallery.DAO.ICategoryRep;
import com.gallery.gallery.DAO.IImageRep;
import com.gallery.gallery.DAO.ITagRep;
import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Image;
import com.gallery.gallery.entity.ImageFull;
import com.gallery.gallery.entity.Tag;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImageService implements IImageService {

    @Autowired
    private EntityManager em;
    @Autowired
    private IImageRep imageRep;
    @Autowired
    private ITagRep tagRep;
    @Autowired
    private ICategoryRep categoryRep;

    public Image saveImage(ImageUpload imageUpload) {
        MultipartFile file = imageUpload.getFile();
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        Image image = new Image();

        try {
            if(imageName.contains("..")) {
                throw new NotFoundException("Filename contains invalid path sequence " + imageName);
            }
            String imageString = imageName.substring(0, imageName.lastIndexOf("."));
            ResizedImage resizedImage = ImageResizeUtil.resize(file.getBytes());
            ImageFull imageFull = new ImageFull();
            imageFull.setData(file.getBytes());

            image.setName(imageString);
            image.setType(file.getContentType());
            image.setSize(file.getSize());
            image.setData(resizedImage.getData());
            image.setDescription(imageUpload.getDescription());
            image.setHeight(resizedImage.getHeight());
            image.setWidth(resizedImage.getWidth());
            image.setImageFull(imageFull);
            image.setCategories(categoryRep.findByIdIn(extractIds(imageUpload.getCategories())));
            image.setTags(tagRep.findByIdIn(extractIds(imageUpload.getTags())));

            return imageRep.save(image);
        } catch (Exception exception) {
            return null;
        }
    }

    public Image getImage(Long imageId) {
            Optional<Image> imageOptional = imageRep.findById(imageId);
            if(imageOptional.isPresent()){
                return imageOptional.get();
            }
            else{
                return null;
            }
    }

    public List<Image> getAllImages() {
        return imageRep.findAll();
    }

    public String deleteImage(Long imageId) {

        try {
            imageRep.deleteById(imageId);
            return "\"Success\"";
        }
        catch (Exception err) {
            return err.toString();
        }
    }

    public Image updateImage(Long id, ImageUpdate imageUpdate ) {
        Optional<Image> optionalImage = imageRep.findById(id);
        Image image;

        if (optionalImage != null){
            image = optionalImage.get();
            image.setCategories(categoryRep.findByIdIn(extractIds(imageUpdate.getCategories())));
            image.setTags(tagRep.findByIdIn(extractIds(imageUpdate.getTags())));
            image.setName(imageUpdate.getName());
            image.setDescription(imageUpdate.getDescription());
            image.setDate(imageUpdate.getDate());
            return  imageRep.save(image);
        } else {
            return null;
        }
    }

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

    public List<Image> getAllImagesBySearch(String searchParams) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        Join<Image, Tag> tags = root.join("tags", JoinType.LEFT);
        Join<Image, Category> categories = root.join("categories", JoinType.LEFT);
        cq.select(root);

        List<Long> categoriesIds = extractIds(searchParams.substring(13, searchParams.indexOf("tagsNames:") + 1));
        String tagsString = searchParams.substring(
                searchParams.indexOf("tagsNames:") + 10, searchParams.indexOf("searchString:"));
        List<String> tagsArray = new ArrayList<>();

        if (!tagsString.isEmpty()) {
            tagsArray = Arrays.asList(tagsString.split(","));
        }
        String searchString = searchParams.substring(searchParams.indexOf("searchString:") + 13);

        Predicate predicate = null;
        Predicate searchPredicate = null;
        Predicate tagsSearchPredicate = null;
        Predicate categoriesSearchPredicate = null;

        if (!searchString.isEmpty()) {
            Predicate namePredicate = cb.like(root.get("name"), "%" + searchString + "%");
            Predicate descriptionPredicate = cb.like(root.get("description"), "%" + searchString + "%");
            searchPredicate = cb.or(namePredicate, descriptionPredicate);
        }
        if (tagsArray.size() > 0) {
            tagsSearchPredicate = cb.equal(tags.get("name"), tagsArray.get(0));
            if (tagsArray.size() > 1) {
                for (int i = 1; i < tagsArray.size(); i++) {
                    Predicate currentPredicate = cb.equal(tags.get("name"), tagsArray.get(i));
                    tagsSearchPredicate = cb.or(tagsSearchPredicate, currentPredicate);
                }
            }
        }
        if (categoriesIds.size() > 0) {
            categoriesSearchPredicate = cb.equal(categories.get("id"), categoriesIds.get(0));
            if (categoriesIds.size() > 1) {
                for (int i = 1; i < categoriesIds.size(); i++) {
                    Predicate currentPredicate = cb.equal(categories.get("id"), categoriesIds.get(i));
                    categoriesSearchPredicate = cb.or(categoriesSearchPredicate, currentPredicate);
                }
            }
        }

        if (searchPredicate != null) {
            predicate = searchPredicate;
        }

        if (tagsSearchPredicate != null) {
            if (predicate != null) {
                predicate = cb.and(predicate, tagsSearchPredicate);
            } else {
                predicate = tagsSearchPredicate;
            }
        }

        if (categoriesSearchPredicate != null) {
            if (predicate != null) {
                predicate = cb.and(predicate, categoriesSearchPredicate);
            } else {
                predicate = categoriesSearchPredicate;
            }
        }

        if (predicate != null) {

            cq.where(predicate);
            List<Image> filteredImages = new ArrayList<>(
                    new HashSet<>(em.createQuery(cq).getResultList()));
            return filteredImages;
        } else {
            return imageRep.findAll();
        }

    }

    private List<Long> extractIds(String value){
        if (value == null || value.isEmpty() || value.substring(1, value.length() - 1).isEmpty()){
            return Collections.emptyList();
        } else {
            return Arrays.stream(value.substring(1, value.length() - 1).split(","))
                    .map(Long::valueOf).collect(Collectors.toList());
        }
    }

}