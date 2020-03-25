package bsuir.service.impl;

import bsuir.dao.dao.impl.UserDao;
import bsuir.dao.entity.User;
import bsuir.service.CrudService;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UserService implements CrudService<User> {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    @Override
    public void create(User user) {
        while (idAlreadyExist(user.getId())) {
            Random random = new Random(2147483647);
            user.setId(random.nextInt());
        }
        userDao.save(user);
    }

    @Override
    public User get(int id) {
        Optional<User> optional = userDao.get(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        Optional<User> optional = userDao.get(id);
        optional.ifPresent(event -> userDao.delete(event));
    }

    private boolean idAlreadyExist(int id) {
        return userDao.getAll().stream().anyMatch(l -> l.getId() == id);
    }

    public boolean loginAlreadyExist(String login) {
        return userDao.getAll().stream().anyMatch(l -> l.getLogin().equals(login));
    }
}
