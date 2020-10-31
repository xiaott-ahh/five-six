package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.entity.Menu;
import com.fivesix.fivesixserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/api/menu")
    public List<Menu> getMenusOfUser() {
        return menuService.listAllByCurrUser();
    }

    @GetMapping("/api/admin/role/menu")
    public List<Menu> getMenusOfRole() {
        return menuService.listAllByRole(1);
    }

}
