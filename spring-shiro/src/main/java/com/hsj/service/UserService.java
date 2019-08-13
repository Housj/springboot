package com.hsj.service;

import com.hsj.model.User;
import org.springframework.stereotype.Service;

/**
 * @author sergei
 * @create 2019-08-07
 */
public interface UserService {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    User selectById(Long id);

    String selectByUsername(String username);

    User selectByUsernameAndPassword(String username, String password);

    int updateByPrimaryKeySelective(User record);

}