package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.User;
import com.fivesix.fivesixserver.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final UserMapper userMapper;

    public UserService (UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public User getUserById(int id) {
        User user = userMapper.getById(id);
        if (user == null) {
            throw new RuntimeException("user not found!");
        }
        return user;
    }

    public boolean exist(User user) {
        return userMapper.getByName(user.getName()) != null;
    }

    public User getUserByName(String name) {
        return userMapper.getByName(name);
    }

    public List<User> fetchAll(int pageIndex) {
        int pageSize = 100;
        return userMapper.getAll((pageIndex-1) * pageSize,pageSize);
    }

    public User login(User user) {
        if (exist(user)) {
            return user;
        }else {
            throw new RuntimeException("no user found!");
        }
    }

    public User register(User user) {
        if (exist(user)) {
            throw new RuntimeException("name has already been registered.");
        }else {
            userMapper.insert(user);
            return user;
        }
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public void deleteUser(User user) {
        userMapper.delete(user);
    }
}
