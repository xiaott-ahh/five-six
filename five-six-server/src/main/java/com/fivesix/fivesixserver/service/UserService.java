package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.User;
import com.fivesix.fivesixserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getUserById(long id) {
        User user = userMapper.getById(id);
        if (user == null) {
            throw new RuntimeException("user not found!");
        }
        return user;
    }

    public User getUserByName(String name) {
        User user = userMapper.getByName(name);
        if (user == null) {
            throw new RuntimeException("user not found!");
        }
        return user;
    }

    public List<User> fetchAll(int pageIndex) {
        int pageSize = 100;
        return userMapper.getAll((pageIndex-1) * pageSize,pageSize);
    }

    public User login(String name, String password) {
        User user = userMapper.getByName(name);
        if (user != null && (user.getPassword().equals(password))) {
            return user;
        }
        throw new RuntimeException("login failed");
    }

    public User register(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userMapper.insert(user);
        return user;
    }

    public void updateUser(Long id, String name) {
        User user = getUserById(id);
        user.setName(name);
        userMapper.update(user);
    }

    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }
}
