package com.fivesix.fivesixserver.mapper;

import com.fivesix.fivesixserver.entity.Menu;
import com.fivesix.fivesixserver.entity.MenuExample;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}