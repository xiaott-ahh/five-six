package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.dao.UserDao;
import com.fivesix.fivesixserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean exist(String name) {
        return getByName(name) == null ? false : true;
    }

    public User getByName(String name) {
        return userDao.findByUsername(name);
    }

    public User get(String name, String password) {
        return userDao.getByUsernameAndPassword(name,password);
    }

    public void add(User user) {
        userDao.save(user);
    }
}
