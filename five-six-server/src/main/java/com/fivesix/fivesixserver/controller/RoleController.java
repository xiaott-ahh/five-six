package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.entity.Role;
import com.fivesix.fivesixserver.result.Result;
import com.fivesix.fivesixserver.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @GetMapping("api/admin/role")
    public List<Role> listAll() {
        return roleService.list();
    }

    @PostMapping("api/admin/role/delete")
    public Result delete(@RequestBody Role role) {
        try{
            roleService.delete(role);
            return new Result(200,"删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(400,"删除失败");
        }
    }

    @PutMapping("api/admin/role/update")
    public Result update(@RequestBody Role role) {
        try{
            roleService.update(role);
            return new Result(200,"更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(400,"更新失败");
        }
    }

    @PostMapping("api/admin/role/new")
    public Result add(@RequestBody Role role) {
        try{
            roleService.add(role);
            return new Result(200,"新建角色成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(400,"新建角色失败");
        }
    }
}
