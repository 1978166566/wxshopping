package com.cssl.dao;

import com.cssl.pojo.Site;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SiteMapper {
    /**
     * 查询该用户的默认收货地址
     * @return
     */
      public Map<String,String> selSiteDft(int u_id);

    /**
     * 清除该用户下的默认地址
     * @param u_id
     * @return
     */
      public int setDefaultZore(int u_id);

    /**
     * 添加site表信息
     * @param u_id
     * @param u_name
     * @param s_region
     * @param s_d_address
     * @return
     */
      public int insertSite(int u_id,String u_name,String s_region,String s_d_address);

    /**
     * 更新用户表的邮箱和电话
     * @param u_email
     * @param u_phone
     * @param u_id
     * @return
     */
      public int setEmailPhone(String u_email,String u_phone,int u_id);

    /**
     * 删除site默认地址表数据
     * @param s_id
     * @return
     */
      public int delSite(int s_id);

    /**
     * 获取删除之后最大的地址编号
     * @param u_id
     * @return
     */
      public int selMaxSid(Integer u_id);

    /**
     * 修改后产生默认地址
     * @param u_id
     * @param s_id
     * @return
     */
      public int updateSiteDFT(int u_id,int s_id);

    /**
     * 查询单用户地址数
     * @param u_id
     * @return
     */
      public int selSitecount(int u_id);

    /**
     * 修改地址表数据
     * @param u_name
     * @param s_region
     * @param s_d_address
     * @param s_id
     * @return
     */
      public int updateSite(String u_name,String s_region,String s_d_address,int s_id);

    /**
     * 修改用户表的电话,邮箱
     * @param u_phone
     * @param u_email
     * @param u_id
     * @return
     */
      public int updateUser(String u_phone,String u_email,int u_id);
    /**
     * 查询用户的默认收货地址
     * @param id 用户id
     * @return
     */
    public Site selordersite(@Param("u_id") int id);
}
