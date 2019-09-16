package com.gallery.gallery.service.implementations;

import com.gallery.gallery.DAO.ICategoryRep;
import com.gallery.gallery.DAO.IImageRep;
import com.gallery.gallery.DAO.ITagRep;
import com.gallery.gallery.entity.Category;
import com.gallery.gallery.entity.Image;
import com.gallery.gallery.entity.ImageFull;
import com.gallery.gallery.entity.Tag;
import com.gallery.gallery.exceptions.FileStorageException;
import com.gallery.gallery.exceptions.MyFileNotFoundException;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
                throw new MyFileNotFoundException("Filename contains invalid path sequence " + imageName);
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
//   public Image saveImage(MultipartFile file, String description, Set<Category> categories, Set<Tag> tags) {
//        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//            if(imageName.contains("..")) {
//                throw new MyFileNotFoundException("Filename contains invalid path sequence " + imageName);
//            }
//            String imageString = imageName.substring(0, imageName.lastIndexOf("."));
//
//            ResizedImage resizedImage = ImageResizeUtil.resize(file.getBytes());
//
//            ImageFull full = new ImageFull();
//            full.setData(file.getBytes());
//            Image image = new Image(imageString, file.getContentType(), file.getSize(),
//                    resizedImage.getData(), description, resizedImage.getHeight(), resizedImage.getWidth(), full, categories, tags);
//            return imageRep.save(image);
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + imageName + ". Please try again!", ex);
//        }
//    }

    public Image getImage(Long imageId) {
        return imageRep.findById(imageId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + imageId));
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

    public List<Image> customFindByNameDes(String text) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        cq.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if(text!=null) {
            predicates.add(cb.or(
                    cb.like(root.get("name"), "%" + text + "%"),
                    cb.like(root.get("description"), "%" + text + "%"))
            );
        }
        return (List<Image>) cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public List<Image> customFindByAny(String text) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
        Root<Image> root = cq.from(Image.class);
        cq.select(root);
        List<Predicate> predicates = new ArrayList<>();
        if(text!=null) {
            predicates.add(cb.or(
                    cb.like(root.get("name"), "%" + text + "%"),
                    cb.like(root.get("description"), "%" + text + "%"))
            );
        }
        return (List<Image>) cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    // UPDATE ------------------
//    CriteriaUpdate<Item> criteriaUpdate = cb.createCriteriaUpdate(Item.class);
//    Root<Item> root = criteriaUpdate.from(Item.class);
//criteriaUpdate.set("itemPrice", newPrice);
//criteriaUpdate.where(cb.equal(root.get("itemPrice"), oldPrice));
//
//    Transaction transaction = session.beginTransaction();
//session.createQuery(criteriaUpdate).executeUpdate();
//transaction.commit();

    // END UPDATE -----------


//    CriteriaQuery<Pet> cq = cb.createQuery(Pet.class);
//    Metamodel m = em.getMetamodel();
//    EntityType<Pet> Pet_ = m.entity(Pet.class);
//
//    Root<Pet> pet = cq.from(Pet.class);
//    Join<Pet, Owner> owner = pet.join(Pet_.owners);


//    List<Image> findImagesByCategoriesTags(List<Integer> categories, List<Integer> tags) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Image> cq = cb.createQuery(Image.class);
//        Root<Image> root = cq.from(Image.class);
////        Class<? extends Category> sublcass = Image.class;
//        cq.select(root);
//
//        cq.where(
//                cb.and(
//                        cb.equal(root.get("id"), categories),
//                        cb.equal(root.type(), sublcass)
//                )
//        );
//
//        List<Topic> topics = entityManager
//                .createQuery(criteria)
//                .getResultList();
//
//        assertEquals(1, topics.size());
//
//
//
//
//        List<Predicate> predicatesCategoriess = new ArrayList<Predicate>();
//        List<Predicate> predicatesTags = new ArrayList<Predicate>();
//
//        Predicate<List> parentExpression = root.get(Image.);
//        Predicate parentPredicate = parentExpression.in(categories);
//        cq.where(parentPredicate);
//        cq.orderBy(cb.asc(root.get(Image.Parent));
//
//        cq.getResultList();
//
//
//
//
//        if (categories != null) {
//            predicatesCategoriess.add(
//                    cb.equal(image.get("someAttribute"), categories));
//        }
//
//        Predicate [] predicatesarr = predicates.toArray(new Predicate[predicates.size()]);
//        Predicate authorNamePredicate = cb.equal(image.get("author"), authorName);
//        Predicate titlePredicate = cb.like(image.get("title"), "%" + title + "%");
//        cq.where(authorNamePredicate, titlePredicate);
//
//        TypedQuery<Image> query = em.createQuery(cq);
//        return query.getResultList();
//    }


//    public List<Image> findByLikeCriteria(String text){
//        return employeeDAO.findAll(new Specification<Employee>() {
//            @Override
//            public Predicate findByLike(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<>();
//                if(text!=null) {
//                    predicates.add(criteriaBuilder.or(
//                            criteriaBuilder.like(root.get("employeeName"), "%" + text + "%"),
//                            criteriaBuilder.like(root.get("employeeEmail"), "%" + text + "%"))
//                    );
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        });
//    }

}