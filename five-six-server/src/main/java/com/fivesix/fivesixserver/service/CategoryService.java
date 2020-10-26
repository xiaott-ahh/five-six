package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.Category;
import com.fivesix.fivesixserver.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    public Category get(int id) {
        Category c = Optional.ofNullable(categoryMapper.getById(id)).orElse(null);
        return c;
    }
}
