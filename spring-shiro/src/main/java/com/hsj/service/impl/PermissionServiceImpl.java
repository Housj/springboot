package com.hsj.service.impl;

import com.hsj.dao.PermissionMapper;
import com.hsj.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author sergei
 * @create 2019-08-08
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionMapper permissionMapper;

    @Override
    public Set<String> findPermissionByUserId(Long uId) {
        return permissionMapper.findPermissionByUserId(uId);
    }
}
