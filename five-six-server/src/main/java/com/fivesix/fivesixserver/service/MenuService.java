package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.*;
import com.fivesix.fivesixserver.mapper.MenuMapper;
import com.fivesix.fivesixserver.mapper.RoleMenuMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    public Menu get(Menu menu){
        return menuMapper.selectByPrimaryKey(menu.getId());
    }

    public List<Menu> getAllByParentId(int pid) {
        MenuExample example = new MenuExample();
        example.createCriteria().andParentIdEqualTo(pid);
        return menuMapper.selectByExample(example);
    }


    public List<Menu> list() {
        MenuExample example = new MenuExample();
        example.setOrderByClause("id desc");
        return menuMapper.selectByExample(example);
    }

    public void update(Menu menu) {
        menuMapper.updateByPrimaryKey(menu);
    }

    public void delete(Menu menu) {
        menuMapper.deleteByPrimaryKey(menu.getId());
        roleMenuService.deleteByMenu(menu);
    }

    public List<Menu> listAllByRole(int rid) {
        List<Menu> res = new ArrayList<>();
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRidEqualTo(rid);
        List<RoleMenu> rms = roleMenuMapper.selectByExample(example);
        for( RoleMenu rm : rms) {
            res.add(menuMapper.selectByPrimaryKey(rm.getMid()));
        }

        res = res.stream().distinct().collect(Collectors.toList());
        handleMenus(res);
        return res;
    }

    public List<Menu> listAllByCurrUser() {
        List<Menu> res = new ArrayList<>();
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getUserByName(username);

        //查询当前用户的所有角色id
        List<Integer> rids = roleService.listRolesOfUser(user).stream().map(Role::getId).collect(Collectors.toList());

        //查询所有角色对应的菜单
        for (int rid : rids) {
            res.addAll(listAllByRole(rid));
        }
        res = res.stream().distinct().collect(Collectors.toList());
        handleMenus(res);
        return res;

    }

    private void handleMenus(List<Menu> menus){
        menus.forEach(menu ->
            menu.setChildren(getAllByParentId(menu.getId()))
        );

        menus.removeIf(m -> m.getParentId() != 0);
    }
}
