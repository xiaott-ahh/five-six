package com.fivesix.fivesixserver.mapper;

import com.fivesix.fivesixserver.entity.RoleMenu;
import com.fivesix.fivesixserver.entity.RoleMenuExample;
import java.util.List;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    List<RoleMenu> selectByExample(RoleMenuExample example);

    RoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);
}