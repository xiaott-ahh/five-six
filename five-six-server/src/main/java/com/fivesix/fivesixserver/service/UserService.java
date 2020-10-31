package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.Role;
import com.fivesix.fivesixserver.entity.User;
import com.fivesix.fivesixserver.entity.UserExample;
import com.fivesix.fivesixserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;

    /*
    根据主键查询，不包含角色信息
     */
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /*
    按名字查询某个用户，包含角色信息
     */
    public User getUserByName(String name) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) return null;
        User user = users.get(0);
        List<Role> roles = roleService.listRolesOfUser(user);
        user.setRoles(roles);
        return user;
    }

    /*
    返回用户列表，包含角色信息
     */
    public List<User> listAll() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("id desc");
        List<User> users = userMapper.selectByExample(userExample);
        users.forEach(user -> user.setRoles(roleService.listRolesOfUser(user)));
        return users;
    }

    /*
    根据用户名判断用户是否存在
     */
    public boolean exist(User user) {
        return getUserByName(user.getUsername()) != null;
    }


    /*
    新注册用户，设置默认角色为访客，对于uid为3
     */
    public void register(User user) {
        if (exist(user)) {
            throw new RuntimeException("name has already been registered.");
        }else {
            userMapper.insert(user);
            userRoleService.setRoles(user,List.of(3));
        }
    }

    /*
    更新角色的相关信息，包括其对应的角色
     */
    public void updateUser(User user) {
        userRoleService.setRoles(user,user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
        userMapper.updateByPrimaryKeySelective(user);
    }

    /*
    删除用户的相关信息，包括其对应的角色信息
     */
    public void deleteUser(User user) {
        userMapper.deleteByPrimaryKey(user.getId());
        userRoleService.deleteByUser(user);
    }

}
