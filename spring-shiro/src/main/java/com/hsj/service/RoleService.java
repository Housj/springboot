package com.hsj.service;

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author sergei
 * @create 2019-08-08
 */
public interface RoleService {
    Set<String> findRoleByUserId(Long id);
}
