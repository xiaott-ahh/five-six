package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.entity.Movie;
import com.fivesix.fivesixserver.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    /*
    前台请求接口
     */
    @GetMapping("/api/movies")
    public List<Movie> list() throws Exception {
        System.out.println("load all movies sorted by rate successfully.");
        return movieService.list().stream().sorted(Comparator.comparingDouble(Movie::getRate).reversed()).collect(Collectors.toList());
    }

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

    @GetMapping("/api/search")
    public List<Movie> listByKeywords(@RequestParam("keywords") String keywords) {
        if (!keywords.equals("")){
            System.out.println("search result returned.");
            return movieService.listByKeywords(keywords).stream().sorted(Comparator.comparing(Movie::getDate).reversed()).collect(Collectors.toList());
        }else{
            return null;
        }
    }

    /*
    以下为后台请求接口
     */
    @PostMapping("/api/admin/content/movie/update")
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

    @PostMapping("/api/admin/content/movie/delete")
    public void delete(@RequestBody Movie movie) throws Exception{
        movieService.deleteById(movie.getId());
        System.out.println("delete movie by id successfully.");
    }

    /*
    电影封面上传
     */
    @PostMapping("/api/admin/content/movie/cover")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:/workspace/fivesix/img";
        File imageFolder = new File(folder);
        //对文件重命名，保留文件的格式png/jpg
        String newName = UUID.randomUUID().toString();
        File f = new File(imageFolder, newName + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
