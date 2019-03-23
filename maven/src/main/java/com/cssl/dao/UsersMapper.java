package com.cssl.dao;

import com.cssl.pojo.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {

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
    public int selbyusername(@Param("u_name") String username);

    /**
     * 注册
     * @param users 注册的对象
     * @return 是否注册成功
     */
    public int regist(@Param("user") Users users);

}
