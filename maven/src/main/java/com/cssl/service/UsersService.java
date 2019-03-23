package com.cssl.service;

import com.cssl.dao.UsersMapper;
import com.cssl.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Size;
import java.util.List;

public interface UsersService {
    /**
     * 登录
     * @param
     * @return
     */
    public Users Login(String u_name,String u_pwd);
    /**
     * 查询用户名是否已存在
     * @param username 用户名
     * @return 存在返回1不存在返回0
     */
    public int selbyusername(String username);

    /**
     * 注册
     * @param users 注册的对象
     * @return 是否注册成功
     */
    public int regist(Users users);
}
