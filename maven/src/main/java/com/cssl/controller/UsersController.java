package com.cssl.controller;
import com.cssl.pojo.Users;
import com.cssl.service.UsersService;
import com.cssl.util.GetMessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yzm.text;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Controller
public class UsersController {
    /**
     * 用户登录/将记住密码的用户加入cookie中
     */
    @Autowired
    private UsersService usersService;
    @ResponseBody
    @RequestMapping(value = "/get")
     public String getUsers(String u_name, String u_pwd, boolean auto, HttpSession session, HttpServletRequest request, HttpServletResponse response)    {
        //将用户添加到cookie
        Users users=usersService.Login( u_name, u_pwd);
       if(users!=null){
           Cookie[] cookies=request.getCookies();//获取cookie
           String value=null;
           if(cookies != null){
               for(Cookie cookie : cookies){
                   if(cookie.getName().equals(u_name)){
                       value= cookie.getValue();//获取存储的数据
                   }
               }
           }
       //登录成功
           if(auto){
                   //第一次存入
               if(value==null) {
                   Cookie cookie = new Cookie(u_name, u_pwd);
                   response.addCookie(cookie);
               }else{
                   for(Cookie cookie : cookies){
                       if(cookie.getName().equals(u_name)){
                           cookie.setMaxAge(0);//移除cookie中的存在的用户
                           response.addCookie(cookie);
                       }
                   }
                   Cookie cookie = new Cookie(u_name, u_pwd);
                   response.addCookie(cookie);//再次添加
               }
           }else{
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals(u_name)){
                           cookie.setMaxAge(0);//移除cookie中的用户
                            response.addCookie(cookie);
                        }
                    }
           }
           session.setAttribute("getUser", users);//保存用户到session 1`
        return "n";
       }
         return "y";
     }

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    @RequestMapping("/selbyusername")
    @ResponseBody
    public int selbyusername(String username)
    {
        int isempty=usersService.selbyusername(username);
        if(isempty>0) {
            return 0;
        }else{
            return 1;
        }
    }

    /**
     * 获取邮箱验证码
     * @param email
     * @return
     */
    @PostMapping("/emailyzm")
    @ResponseBody
    public String emailyzm(String email)
    {
        try {
            String emailyzm= text.sendEmail(email);
            return emailyzm;
        } catch (Exception e) {
            return "n";
        }
    }

    /**
     * 获取短信验证码
     * @param phone
     * @return
     */
    @PostMapping("/phoneyzm")
    @ResponseBody
    public String phoneyzm(String phone)
    {
        try {
            String phoneyzm= GetMessageCode.getCode(phone);
            return phoneyzm;
        } catch (Exception e) {
            return "n";
        }
    }

    /**
     * 全部信息输入正确之后执行注册
     * @param user
     * @return
     */
    @RequestMapping("/regist")
    public String regist(Users user)
    {
        int i=usersService.regist(user);
        if(i>0){
            return "login";
        }
        return "register";
    }
}
