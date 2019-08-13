package com.hsj.dao;

import com.hsj.model.UserRole;
import org.springframework.stereotype.Repository;


public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}