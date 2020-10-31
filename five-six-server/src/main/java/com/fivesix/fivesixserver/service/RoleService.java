package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.*;
import com.fivesix.fivesixserver.mapper.RoleMapper;
import com.fivesix.fivesixserver.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;

    /*
    给Role的menus/perms字段设值，没有采用直接从数据库中关联表取值的做法
     */
    public void setMenusAndPerms(Role role) {
        List<Integer> menuIds = menuService.listAllByRole(role.getId()).stream().map(Menu::getId).collect(Collectors.toList());
        List<Integer> permIds = permissionService.listPermissionsOfRole(role).stream().map(Permission::getId).collect(Collectors.toList());
        role.setMenuIds(menuIds);
        role.setPermIds(permIds);
    }

    /*
    根据角色的id取得对应的角色，包含数据库中不存在的字段 menus、perms
     */
    public Role get(int id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        setMenusAndPerms(role);
        return role;
    }

    /*
    返回所有的角色，包含数据库中不存在的字段 menus、perms
     */
    public List<Role> list() {
        RoleExample example = new RoleExample();
        example.setOrderByClause("id desc");
        List<Role> roles = roleMapper.selectByExample(example);
        roles.forEach(this::setMenusAndPerms);
        return roles;
    }

    /*
    根据用户返回其对应的角色列表，不包含menus/perms字段
     */
    public List<Role> listRolesOfUser(User user) {
        List<Role> roles = new ArrayList<>();
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }
        return roles;
    }


    public Set<String> listRoleNamesOfUser(User user) {
        Set<String> names = new HashSet<>();
        List<Role> roles = listRolesOfUser(user);
        for (Role r : roles) {
            names.add(r.getName());
        }
        return names;
    }

    /*
    新增Role
     */
    public void add(Role role) {
        roleMapper.insert(role);
    }

    /*
    删除角色，同时需要处理RoleMenu/RolePermission表
     */
    public void delete(Role role) {
        roleMapper.deleteByPrimaryKey(role.getId());
        userRoleService.deleteByRole(role);
        rolePermissionService.deleteByRole(role);
        roleMenuService.deleteByRole(role);
    }

    /*
    更新角色的相关配置，同时需要处理RoleMenu/RolePermission表
     */
    public void update(Role role) {
        System.out.println(role.getMenuIds());
        List<Integer> menuIds = role.getMenuIds();
        List<Integer> permIds = role.getPermIds();
        roleMenuService.setMenus(role,menuIds);
        rolePermissionService.setPermissions(role,permIds);
        roleMapper.updateByPrimaryKeySelective(role);
    }
}
