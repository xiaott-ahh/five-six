package com.fivesix.fivesixserver.mapper;

import com.fivesix.fivesixserver.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM `users` WHERE `id`=#{id}")
    User getById(@Param("id") int id);


    @Select("SELECT * FROM `users` WHERE name=#{name}")
    User getByName(@Param("name") String name);

    @Select("SELECT * FROM `users` LIMIT #{offset},#{maxResults}")
    List<User> getAll(@Param("offset") int offset, @Param("maxResults") int maxResults);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("INSERT INTO `users` (`name`,`password`,`salt`) VAlUES (#{user.name},#{user.password},#{user.salt})")
    void insert(@Param("user") User user);

    @Update("UPDATE `users` SET `name` = #{user.name},`password`= #{user.password} WHERE id = #{user.id}")
    void update(@Param("user") User user);

    @Delete("DELETE FROM `users` WHERE `id` = #{user.id}")
    void delete(@Param("user") User user);

}
