package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.entity.Movie;
import com.fivesix.fivesixserver.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @CrossOrigin
    @GetMapping("/api/movies")
    public List<Movie> list() throws Exception {
        System.out.println("succeess");
        return movieService.list();
    }

    @CrossOrigin
    @PostMapping("/api/movies")
    public Movie add(@RequestBody Movie movie) throws Exception {
        movieService.save(movie);
        return movie;
    }

    @CrossOrigin
    @GetMapping("/api/categories/{cid}/movies")
    public List<Movie> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (cid != 0) {
            return movieService.listByCategory(cid);
        }else {
            return list();
        }
    }

    @CrossOrigin
    @GetMapping("/api/director/{name}/movies")
    public List<Movie> listByDirector(@PathVariable("name") String name) throws Exception {
        if (name != "") {
            return movieService.listByDirector(name);
        }else {
            return list();
        }
    }

    @CrossOrigin
    @GetMapping("/api/title/{name}/movies")
    public Movie getByMovieName(@PathVariable("name") String title) throws Exception {
        if (title != "") {
            return movieService.getByMovieName(title);
        }else {
            return null;
        }
    }

    @CrossOrigin
    @PostMapping("/api/delete")
    public void delete(@RequestBody Movie movie) throws Exception{
        movieService.deleteById(movie.getId());
    }
}
