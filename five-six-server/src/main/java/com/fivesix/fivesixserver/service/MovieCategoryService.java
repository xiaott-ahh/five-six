package com.fivesix.fivesixserver.service;


import com.fivesix.fivesixserver.entity.Category;
import com.fivesix.fivesixserver.entity.Movie;
import com.fivesix.fivesixserver.mapper.MovieCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCategoryService {

    @Autowired
    MovieCategoryMapper movieCategoryMapper;

    void add(Movie movie) {
        for (Category category : movie.getCategories()) {
            movieCategoryMapper.insert(movie,category);
        }
    }

    void delete(Movie movie) {
        movieCategoryMapper.delete(movie);
    }
}
