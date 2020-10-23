package com.fivesix.fivesixserver.mapper;

import com.fivesix.fivesixserver.entity.Category;
import com.fivesix.fivesixserver.entity.Movie;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MovieMapper {

    @Select("SELECT * FROM `movies` WHERE `id` = #{id}")
    @Results ({
        @Result(
            column = "cid",property = "category",
            one = @One(select = "com.fivesix.fivesixserver.mapper.CategoryMapper.getById")
        )
    })
    Movie get(@Param("id") int id);

    @Select("SELECT * FROM `movies`")
    @Results ({
            @Result(
                    column = "cid",property = "category",
                    one = @One(select = "com.fivesix.fivesixserver.mapper.CategoryMapper.getById")
            )
    })
    List<Movie> getAll();

    @Select("SELECT * FROM `movies` WHERE `cid` = #{category.id}")
    @Results ({
            @Result(
                    column = "cid",property = "category",
                    one = @One(select = "com.fivesix.fivesixserver.mapper.CategoryMapper.getById")
            )
    })
    List<Movie> getAllByCategory(@Param("category") Category category);

    @Select("SELECT * FROM `movies` WHERE `title` = #{title}")
    @Results ({
            @Result(
                    column = "cid",property = "category",
                    one = @One(select = "com.fivesix.fivesixserver.mapper.CategoryMapper.getById")
            )
    })
    Movie getByMovieName(@Param("title") String title);

    @Select("SELECT * FROM `movies` WHERE `director` = #{director}")
    @Results ({
            @Result(
                    column = "cid",property = "category",
                    one = @One(select = "com.fivesix.fivesixserver.mapper.CategoryMapper.getById")
            )
    })
    List<Movie> getAllByDirectorName(@Param("director") String director);

    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("INSERT INTO `movies`(`cover`,`title`,`director`,`language`,`actors`,`nation`,`rate`,`date`,`abs`,`cid`) VALUES (#{movie.cover},#{movie.title},#{movie.director},#{movie.language},#{movie.actors},#{movie.nation},#{movie.rate},#{movie.date},#{movie.abs},#{movie.category.id})")
    void insert(@Param("movie") Movie movie);

    @Update("UPDATE `movies` SET `title`=#{movie.title},`cover`=#{movie.cover},`director`=#{movie.director},`language`=#{movie.language},`actors`=#{movie.actors},`nation`=#{movie.nation},`rate`=#{movie.rate},`date`=#{movie.date},`abs`=#{movie.abs},`cid`=#{movie.category.id} WHERE `id`=#{movie.id}")
    void update(@Param("movie") Movie movie);

    @Delete("DELETE FROM `movies` WHERE `id` = #{movie.id}")
    void delete(@Param("movie") Movie movie);
}
