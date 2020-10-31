package com.fivesix.fivesixserver.mapper;

import com.fivesix.fivesixserver.entity.Permission;
import com.fivesix.fivesixserver.entity.PermissionExample;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}