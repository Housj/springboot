package com.hsj.dao;

import com.hsj.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Set<String> findRoleByUserId(Long id);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}