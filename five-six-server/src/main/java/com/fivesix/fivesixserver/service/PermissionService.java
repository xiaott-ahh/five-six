package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.*;
import com.fivesix.fivesixserver.mapper.PermissionMapper;
import com.fivesix.fivesixserver.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionService rolePermissionService;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    public Permission get(Permission permission){
        return permissionMapper.selectByPrimaryKey(permission.getId());
    }

    /*
    返回全部的权限列表
     */
    public List<Permission> list(){
        PermissionExample example = new PermissionExample();
        example.setOrderByClause("id desc");
        return permissionMapper.selectByExample(example);
    }

    //返回维护的所有权限url
    public Set<String> listPermissionUrls() {
        return list().stream().map(Permission::getUrl).collect(Collectors.toSet());
    }

    //判断请求的接口是否在权限管理系统中
    public boolean requireFilter(String requestAPI) {
        Set<String> managedUrls = listPermissionUrls();
        for (String url : managedUrls) {
            if (requestAPI.startsWith(url)) return true;
        }
        return false;
    }

    public void add(Permission permission) {
        permissionMapper.insert(permission);
    }

    public void delete(Permission permission) {
        rolePermissionService.deleteByPermission(permission);
        permissionMapper.deleteByPrimaryKey(permission.getId());
    }

    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    /*
    返回角色对应的权限列表
     */
    public List<Permission> listPermissionsOfRole(Role role) {
        List<Permission> res = new ArrayList<>();
        RolePermissionExample rpe = new RolePermissionExample();
        rpe.createCriteria().andRidEqualTo(role.getId());
        List<RolePermission> rps = rolePermissionMapper.selectByExample(rpe);
        for (RolePermission rp : rps ) {
            res.add(permissionMapper.selectByPrimaryKey(rp.getPid()));
        }
        return res.stream().distinct().collect(Collectors.toList());
    }

    /*
    返回用户对应的所有权限
     */
    public List<Permission> listPermissionsOfUser(User user) {
        List<Role> roles = roleService.listRolesOfUser(user);
        List<Permission> res = new ArrayList<>();
        for (Role r : roles) {
            res.addAll(listPermissionsOfRole(r));
        }
        return res.stream().distinct().collect(Collectors.toList());
    }

    //返回当前用户所有的可访问的接口
    public Set<String> listPermissionUrlsOfUser(String username) {
        User user = userService.getUserByName(username);
        return listPermissionsOfUser(user).stream().map(Permission::getUrl).collect(Collectors.toSet());

    }
}
