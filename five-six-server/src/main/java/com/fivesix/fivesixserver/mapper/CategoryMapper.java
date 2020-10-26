package com.fivesix.fivesixserver.mapper;


import com.fivesix.fivesixserver.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {

    @Select("SELECT * FROM `categories` WHERE id IN(SELECT `cid` FROM `movie-category` WHERE `mid`= #{mid})")
    List<Category> getAllByMovieId(@Param("mid") Long mid);

    @Select("SELECT * FROM `categories` WHERE `id` = #{id}")
    Category getById(@Param("id") int id);
}