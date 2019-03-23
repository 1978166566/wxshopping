package com.cssl.serviceimpl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cssl.dao.UsersMapper;
import com.cssl.pojo.Users;
import com.cssl.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersServiceimpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;
    /**
     * 登录
     * @param
     * @return
     */
    @Override
    public Users Login(String u_name,String u_pwd) {
        return usersMapper.Login(u_name, u_pwd);
    }

    /**
     * 查询用户名是否已存在
     * @param username 用户名
     * @return 存在返回1不存在返回0
     */
    @Override
    public int selbyusername(String username)
    {
        return usersMapper.selbyusername(username);
    }

    /**
     * 注册
     * @param users 注册的对象
     * @return 是否注册成功
     */
    @Override
    public int regist(Users users)
    {
        return usersMapper.regist(users);
    }
}
