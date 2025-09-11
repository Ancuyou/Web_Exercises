package vn.iotstar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.entities.Category;
import vn.iotstar.entities.User;
import vn.iotstar.models.CategoryDTO;
import vn.iotstar.utils.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDaoImpl implements CategoryDao {

//    @Override
//    public void create(CategoryDTO categoryDTO) {
//        EntityManager enma = JPAConfig.getEntityManager();
//        EntityTransaction trans = enma.getTransaction();
//        try {
//            trans.begin();
//            Category entity = CategoryMapper.toEntity(categoryDTO);
//            enma.persist(entity);
//            trans.commit();
//        } catch (Exception e) {
//            trans.rollback();
//            throw e;
//        } finally {
//            enma.close();
//        }
//    }
    @Override
    public void create(CategoryDTO categoryDTO) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Category entity = CategoryMapper.toEntity(categoryDTO);

            if (categoryDTO.getUserId() > 0) {
                User userRef = enma.getReference(User.class, categoryDTO.getUserId());
                entity.setUser(userRef);
            } else {
                throw new IllegalArgumentException("UserId không hợp lệ khi insert Category");
            }

            enma.persist(entity);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            enma.close();
        }
    }

    //    @Override
//    public void update(CategoryDTO categoryDTO) {
//        EntityManager enma = JPAConfig.getEntityManager();
//        EntityTransaction trans = enma.getTransaction();
//        try {
//            trans.begin();
//            Category entity = CategoryMapper.toEntity(categoryDTO);
//            enma.merge(entity); // UPDATE
//            trans.commit();
//        } catch (Exception e) {
//            trans.rollback();
//            throw e;
//        } finally {
//            enma.close();
//        }
//    }
    @Override
    public void update(CategoryDTO categoryDTO) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Category entity = CategoryMapper.toEntity(categoryDTO);
            // ép buộc phải có userId
            if (categoryDTO.getUserId() > 0) {
                User userRef = enma.getReference(User.class, categoryDTO.getUserId());
                entity.setUser(userRef);
            } else {
                throw new IllegalArgumentException("UserId không hợp lệ khi update Category");
            }
            enma.merge(entity);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) trans.rollback();
            e.printStackTrace(); // log stacktrace chi tiết
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Category category = enma.find(Category.class, id);
            if (category != null) {
                enma.remove(category); // DELETE
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public CategoryDTO findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            Category category = enma.find(Category.class, id);
            return CategoryMapper.toDTO(category);
        } finally {
            enma.close();
        }
    }

    @Override
    public List<CategoryDTO> findByUserId(int userId) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            TypedQuery<Category> query = enma.createNamedQuery("Category.findByUserId", Category.class);
            query.setParameter("userId", userId);

            return query.getResultList()
                    .stream()
                    .map(CategoryMapper::toDTO)
                    .collect(Collectors.toList());
        } finally {
            enma.close();
        }
    }

    @Override
    public List<CategoryDTO> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);

            return query.getResultList()
                    .stream()
                    .map(CategoryMapper::toDTO)
                    .collect(Collectors.toList());
        } finally {
            enma.close();
        }
    }
}
