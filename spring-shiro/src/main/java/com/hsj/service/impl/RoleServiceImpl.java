package com.hsj.service.impl;

import com.hsj.dao.RoleMapper;
import com.hsj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author sergei
 * @create 2019-08-08
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Set<String> findRoleByUserId(Long id) {
        return roleMapper.findRoleByUserId(id);
    }

    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public RoleMapper getRoleMapper() {
        return roleMapper;
    }
}
