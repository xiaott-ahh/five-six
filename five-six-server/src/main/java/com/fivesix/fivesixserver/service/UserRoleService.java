package com.fivesix.fivesixserver.service;

import com.fivesix.fivesixserver.entity.Role;
import com.fivesix.fivesixserver.entity.User;
import com.fivesix.fivesixserver.entity.UserRole;
import com.fivesix.fivesixserver.entity.UserRoleExample;
import com.fivesix.fivesixserver.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    final private UserRoleMapper userRoleMapper;

    public UserRoleService (UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    public void setRoles(User user,List<Integer> rolesIds) {
        //移除所有该用户的旧角色
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        for (UserRole ur : userRoles) {
            userRoleMapper.deleteByPrimaryKey(ur.getId());
        }

        //创建新的角色
        if (null != rolesIds)
            for (int rid : rolesIds) {
                UserRole userRole = new UserRole();
                userRole.setRid(rid);
                userRole.setUid(user.getId());
                userRoleMapper.insert(userRole);
            }
    }

    public void deleteByUser(User user) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        for (UserRole ur : userRoles) {
            userRoleMapper.deleteByPrimaryKey(ur.getId());
        }
    }

    public void deleteByRole(Role role) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andRidEqualTo(role.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        for (UserRole userRole : userRoles) {
            userRoleMapper.deleteByPrimaryKey(userRole.getId());
        }
    }
}
