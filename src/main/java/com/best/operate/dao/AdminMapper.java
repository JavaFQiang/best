package com.best.operate.dao;

import com.best.operate.model.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin admin);

    int insertSelective(Admin admin);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin admin);

    int updateByPrimaryKey(Admin record);

    List<Admin> adminList();
}