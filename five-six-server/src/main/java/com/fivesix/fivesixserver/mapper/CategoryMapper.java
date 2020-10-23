package com.fivesix.fivesixserver.mapper;


import com.fivesix.fivesixserver.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {

    @Select("SELECT * FROM `category`")
    List<Category> getAll();

    @Select("SELECT * FROM `category` WHERE `id` = #{id}")
    Category getById(@Param("id") int id);
}