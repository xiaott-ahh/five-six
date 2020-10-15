package com.fivesix.fivesixserver.dao;

import com.fivesix.fivesixserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User,Integer> {

    User findByuserName(String name);

    User getByuserNameAndPassword(String name, String password);
}
