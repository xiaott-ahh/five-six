package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.entity.Movie;
import com.fivesix.fivesixserver.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @CrossOrigin
    @GetMapping("/api/movies")
    public List<Movie> list() throws Exception {
        System.out.println("load all movies sorted by rate successfully.");
        return movieService.list().stream().sorted(Comparator.comparingDouble(Movie::getRate).reversed()).collect(Collectors.toList());
    }

    @CrossOrigin
    @PostMapping("/api/movies")
    public Movie save(@RequestBody Movie movie, @RequestParam(value = "changeCategories") String categoriesIsChanged) throws Exception {

        Movie movie1 = movieService.getByMovieName(movie.getTitle());
        if (movie1 != null) {
            if (categoriesIsChanged.equals("true")) {
                movieService.updateMovieAndCategories(movie);
                System.out.println("update movie and categories.");
            } else {
                movieService.update(movie);
                System.out.println("update movie.");
            }
        } else {
            movieService.save(movie);
            System.out.println("add new movie.");
        }
        return movie;
    }

    @CrossOrigin
    @GetMapping("/api/movies/category/{cid}/{dateOrRate}")
    public Object listByCategory(@PathVariable("cid") int cid, @PathVariable("dateOrRate") int dateOrRate) throws Exception {
        List<Movie> res;
        if (cid == 0) {
            res =  movieService.list();
        }else{
            res = movieService.listByCategory(cid);
        }
        if (dateOrRate == 1) return res.stream().sorted(Comparator.comparingDouble(Movie::getRate).reversed()).collect(Collectors.toList());
        else return res.stream().sorted(Comparator.comparing(Movie::getDate).reversed()).collect(Collectors.toList());
    }

    @CrossOrigin
    @GetMapping("/api/search")
    public List<Movie> listByKeywords(@RequestParam("keywords") String keywords) {
        if (!keywords.equals("")){
            System.out.println("search result returned.");
            return movieService.listByKeywords(keywords).stream().sorted(Comparator.comparing(Movie::getDate).reversed()).collect(Collectors.toList());
        }else{
            return null;
        }
    }

    @CrossOrigin
    @PostMapping("/api/delete")
    public void delete(@RequestBody Movie movie) throws Exception{
        movieService.deleteById(movie.getId());
        System.out.println("delete movie by id successfully.");
    }
}
