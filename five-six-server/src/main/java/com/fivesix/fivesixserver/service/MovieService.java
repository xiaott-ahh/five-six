package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.Category;
import com.fivesix.fivesixserver.entity.Movie;
import com.fivesix.fivesixserver.mapper.MovieMapper;
import com.fivesix.fivesixserver.util.CastUtil;
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

    @Autowired
    MovieCategoryService movieCategoryService;

    @Autowired
    RedisService redisService;
    /*
    public List<Movie> list(){
        List<Movie> res = movieMapper.getAll();
        if (res != null) {
            res.sort(Comparator.comparingInt(Movie::getId));
            return res;
        }
        throw new RuntimeException("movie set is empty!");
    }
    */
    public List<Movie> list() {
        List<Movie> res;
        String key = "movieList";
        if (redisService.get(key) == null) {
            res = movieMapper.getAll();
            res.sort(Comparator.comparingInt(Movie::getId));
            redisService.set(key,res);
        } else {
            res = CastUtil.objectConvertToList(redisService.get(key),Movie.class);
        }
        return res;
    }

    public List<Movie> listByPageIndex(int pageIndex) {
        List<Movie> res = movieMapper.getByPageIndex(pageIndex);
        if (res != null) {
            res.sort(Comparator.comparingInt(Movie::getId));
            return res;
        }
        throw new RuntimeException("movie set is emtpy!");
    }

    public List<Movie> listByRate() {
        List<Movie> res = movieMapper.getAll();
        if (res != null) {
            res.sort(Comparator.comparingDouble(Movie::getRate));
        }
        return res;
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

    public List<Movie> listByKeywords(String keywords) {
        return movieMapper.getAllByFuzzySearch(keywords);
    }

    public Movie getByMovieName(String title) {
        return movieMapper.getByMovieName(title);
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

    public void updateMovieAndCategories(Movie movie) {
        movieCategoryService.delete(movie);
        movie.getCategories().forEach(Category::toString);
        movieMapper.update(movie);
        movieCategoryService.add(movie);
        movie.getCategories().forEach(Category::toString);
    }

    public void save(Movie movie) {
        movieMapper.insert(movie);
        movieCategoryService.add(movie);
    }
}
