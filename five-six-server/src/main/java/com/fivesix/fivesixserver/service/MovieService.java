package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.Category;
import com.fivesix.fivesixserver.entity.Movie;
import com.fivesix.fivesixserver.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    CategoryService categoryService;

    @Autowired
    MovieMapper movieMapper;

    public List<Movie> list(){
        List<Movie> res = movieMapper.getAll();
        if (res != null) {
            res.sort(Comparator.comparingInt(Movie::getId));
            return res;
        }
        throw new RuntimeException("movie set is empty");
    }

    public List<Movie> listByCategory(int cid) {
        Category c = categoryService.get(cid);
        if (c != null) {
            List<Movie> res = movieMapper.getAllByCategory(c);
            if (res != null) {
                res.sort(Comparator.comparingInt(Movie::getId));
                return res;
            }
            throw new RuntimeException("no movie found of that category!");
        }
        throw new RuntimeException("no such category!");
    }

    public List<Movie> listByDirector(String director) {
        List<Movie> res = movieMapper.getAllByDirectorName(director);
        if (res != null) {
            res.sort(Comparator.comparingInt(Movie::getId));
            return res;
        } else {
            throw new RuntimeException("no movie found of this director!");
        }
    }

    public Movie getByMovieName(String title) {
        Movie movie = movieMapper.getByMovieName(title);
        if (movie != null) {
            return movie;
        } else {
            throw new RuntimeException("no movie found of this title");
        }
    }

    public void deleteById(int id) {
        Movie movie = movieMapper.get(id);
        if (movie != null) {
            movieMapper.delete(movie);
        }else{
            throw new RuntimeException("no movie found");
        }
    }

    public void update(Movie movie) {
        movieMapper.update(movie);
    }

    public void add(Movie movie) {
        movieMapper.insert(movie);
    }

    public void save(Movie movie) {
        try {
            Movie movie1 = getByMovieName(movie.getTitle());
            update(movie);
        }catch (RuntimeException e) {
            add(movie);
        }
    }
}
