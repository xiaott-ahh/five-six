package com.fivesix.fivesixserver.mapper;

import com.fivesix.fivesixserver.entity.Category;
import com.fivesix.fivesixserver.entity.Movie;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MovieMapper {

    @Select("SELECT * FROM `movies` WHERE `id` = #{id}")
    @Results (id = "categoriesOfMovie",value={
        @Result(
            id = true,column = "id",property = "id"
        ),
        @Result(
            column = "id",property = "categories",
            many = @Many(select = "com.fivesix.fivesixserver.mapper.CategoryMapper.getAllByMovieId")
        )
    })
    Movie get(@Param("id") int id);

    @Select("SELECT * FROM `movies`")
    @ResultMap(value = "categoriesOfMovie")
    List<Movie> getAll();

    @Select("SELECT * FROM `movies` LIMIT #{startIndex},21")
    @ResultMap(value = "categoriesOfMovie")
    List<Movie> getByPageIndex(@Param("startIndex") int startIndex);

    @Select("SELECT * FROM `movies` WHERE `id` IN (SELECT `mid` FROM `movie-category` WHERE `cid` = #{category.id})")
    @ResultMap(value = "categoriesOfMovie")
    List<Movie> getAllByCategory(@Param("category") Category category);

    @Select("SELECT * FROM `movies` WHERE `title` = #{title}")
    @ResultMap(value = "categoriesOfMovie")
    Movie getByMovieName(@Param("title") String title);

    @Select("SELECT * FROM `movies` WHERE `director` = #{director}")
    @ResultMap(value = "categoriesOfMovie")
    List<Movie> getAllByDirectorName(@Param("director") String director);

    @Select("SELECT * FROM `movies` WHERE `director` LIKE CONCAT ('%',#{keywords},'%') OR `actors` LIKE CONCAT('%',#{keywords},'%') OR `title` LIKE CONCAT ('%',#{keywords},'%')")
    @ResultMap(value = "categoriesOfMovie")
    List<Movie> getAllByFuzzySearch(@Param("keywords") String keywords);


    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("INSERT INTO `movies`(`cover`,`title`,`date`,`rate`,`director`,`scriptwriter`,`actors`,`district`,`language`,`duration`,`abs`) VALUES (#{movie.cover},#{movie.title},#{movie.date},#{movie.rate},#{movie.director},#{movie.scriptwriter},#{movie.actors},#{movie.district},#{movie.language},#{movie.duration},#{movie.abs})")
    void insert(@Param("movie") Movie movie);

    @Update("UPDATE `movies` SET `title`=#{movie.title},`cover`=#{movie.cover},`scriptwriter`=#{movie.scriptwriter},`director`=#{movie.director},`language`=#{movie.language},`actors`=#{movie.actors},`district`=#{movie.district},`rate`=#{movie.rate},`date`=#{movie.date},`abs`=#{movie.abs},`duration`=#{movie.duration} WHERE `id`=#{movie.id}")
    void update(@Param("movie") Movie movie);

    @Delete("DELETE FROM `movies` WHERE `id` = #{movie.id}")
    void delete(@Param("movie") Movie movie);
}
