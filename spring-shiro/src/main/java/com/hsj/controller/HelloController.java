package com.hsj.controller;

import com.alibaba.fastjson.JSON;
import com.hsj.model.User;
import com.hsj.service.SessionService;
import com.hsj.service.UserService;
import com.hsj.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sergei
 * @create 2019-08-10
 */

@Controller
public class HelloController {

    Map<String,Object> resultMap = new LinkedHashMap<String,Object>();

    @Autowired
    UserService userService;

//    @Resource
//    SessionService sessionService;

    @RequestMapping(value = "/login2")
    @ResponseBody
    public String login2(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);
            try {
                currentUser.login(token);
                resultMap.put("status",200);
                resultMap.put("message","/list.jsp");
            }
            catch (AuthenticationException ae) {
                resultMap.put("message",ae.getMessage());
            }
        }
        return JSON.toJSONString(resultMap);
    }

//    @RequestMapping(value = "/test")
//    public void test(HttpSession session){
//      session.setAttribute("a","controller");
//      sessionService.testSession();
//    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public ModelAndView welcomePage(){
        ModelAndView model = new ModelAndView();
        User u = userService.selectByPrimaryKey(Long.parseLong("1"));
        model.addObject("title",u.getUsername());
        model.addObject("message",u.getPassword());
        model.setViewName("hello");
        return model;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(User user, Boolean rememberMe, HttpServletRequest request){
        System.out.println(user.toString()+"  "+rememberMe);
        UsernamePasswordToken up = new UsernamePasswordToken(user.getUsername(),user.getPassword(),rememberMe);
        try {
            SecurityUtils.getSubject().login(up);
            System.out.println("1");
            resultMap.put("status",200);
            resultMap.put("message","/list.jsp");
        }catch (DisabledAccountException e) {
            System.out.println("2");
            resultMap.put("status", 500);
            resultMap.put("message", "帐号已经禁用。");
        } catch (Exception e) {
            System.out.println("3");
            resultMap.put("status", 500);
            resultMap.put("message", "帐号或密码错误");
        }
        return resultMap;
    }

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title","Spring Security Hello World");
        model.addObject("message","This is welcome page!");
        model.setViewName("admin");
        System.out.println("admin");
        return model;
    }
}
