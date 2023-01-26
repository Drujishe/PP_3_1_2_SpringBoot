package ru.drujishe.springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.springboot.model.User;

import java.util.List;


@Service
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void add(User user) {
        manager.persist(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        manager.merge(updatedUser);
    }

    @Override
    public void delete(int id) {
        manager.remove(getUserById(id));
    }

    @Override
    public List<User> getAll() {
        return manager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserById(int id) {
        return manager.createQuery("from User user where user.id = " + id, User.class).getSingleResult();
    }
}
