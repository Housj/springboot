package com.hsj.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;

/**
 * @author sergei
 * @create 2019-08-10
 */
//@Component
public class SessionService {

    public void testSession(){
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println(session.getAttribute("a"));
    }
}
