package com.fivesix.fivesixserver.dao;

import com.fivesix.fivesixserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String name);

    User getByUsernameAndPassword(String name, String password);
}
