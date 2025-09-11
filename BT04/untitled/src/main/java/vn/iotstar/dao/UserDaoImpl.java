package vn.iotstar.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JPAConfig;
import vn.iotstar.entities.User;


import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public User findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public User findByUsername(String username) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.userName = :username";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("username", username);

            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public User login(String username, String password) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.userName = :username AND u.passWord = :password";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT u FROM User u";
            return em.createQuery(jpql, User.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void create(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
