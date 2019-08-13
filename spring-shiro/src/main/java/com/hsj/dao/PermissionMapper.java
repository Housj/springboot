package com.hsj.dao;

import com.hsj.model.Permission;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Set<String> findPermissionByUserId(Long uId);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}