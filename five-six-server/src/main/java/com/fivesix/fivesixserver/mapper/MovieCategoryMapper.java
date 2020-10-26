package com.fivesix.fivesixserver.mapper;

import com.fivesix.fivesixserver.entity.Category;
import com.fivesix.fivesixserver.entity.Movie;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface MovieCategoryMapper {

    @Insert("INSERT INTO `movie-category` (`mid`,`cid`) VALUES (#{movie.id},#{category.id})")
    void insert(@Param("movie") Movie movie, @Param("category") Category category);

    @Delete("DELETE FROM `movie-category` WHERE `mid` = #{movie.id}")
    void delete(@Param("movie") Movie movie);
}
