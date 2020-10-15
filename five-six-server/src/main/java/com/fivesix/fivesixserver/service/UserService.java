package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.dao.UserDAO;
import com.fivesix.fivesixserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public boolean exist(String name) {
        return getByName(name) == null ? false : true;
    }

    public User getByName(String name) {
        return userDAO.findByuserName(name);
    }

    public User get(String name,String password) {
        return userDAO.getByuserNameAndPassword(name,password);
    }

    public void add(User user) {
        userDAO.save(user);
    }
}
