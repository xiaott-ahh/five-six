package com.fivesix.fivesixserver.mapper;

import com.fivesix.fivesixserver.entity.Role;
import com.fivesix.fivesixserver.entity.RoleExample;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}