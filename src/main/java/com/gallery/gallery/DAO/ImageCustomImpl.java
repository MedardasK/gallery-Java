package com.gallery.gallery.DAO;

import com.gallery.gallery.entity.Image;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageCustomImpl implements IImageCustomRep {

    @PersistenceContext
    private EntityManager em;

    public List<Image> customFindByNameDes(String text){
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
