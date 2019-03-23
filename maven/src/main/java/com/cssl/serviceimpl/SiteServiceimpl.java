package com.cssl.serviceimpl;

import com.cssl.dao.SiteMapper;
import com.cssl.pojo.Site;
import com.cssl.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SiteServiceimpl implements SiteService {
    @Autowired
    private SiteMapper sitemapper;
    /**
     * 清除该用户下的默认地址
     * @param u_id
     * @return
     */
    @Override
    public int setDefaultZore(int u_id) {
        return sitemapper.setDefaultZore(u_id);
    }
    /**
     * 删除site默认地址表数据
     * @param s_id
     * @return
     */
    @Override
    public int delSite(int s_id) {
        return sitemapper.delSite(s_id);
    }
    /**
     * 修改地址表数据
     * @param u_name
     * @param s_region
     * @param s_d_address
     * @param s_id
     * @return
     */
    @Override
    public int updateSite(String u_name, String s_region, String s_d_address, int s_id) {
        return sitemapper.updateSite(u_name,s_region,s_d_address,s_id);
    }
    /**
     * 修改用户表的电话,邮箱
     * @param u_phone
     * @param u_email
     * @param u_id
     * @return
     */
    @Override
    public int updateUser(String u_phone, String u_email, int u_id) {
        return sitemapper.updateUser(u_phone,u_email,u_id);
    }
    /**
     * 查询用户的默认收货地址
     * @param id 用户id
     * @return
     */
    @Override
    public Site selordersite(int id) {
        return sitemapper.selordersite(id);
    }

    /**
     * 查询单用户地址数
     * @param u_id
     * @return
     */
    @Override
    public int selSitecount(int u_id) {
        return sitemapper.selSitecount(u_id);
    }

    /**
     * 获取删除之后最大的地址编号
     * @param u_id
     * @return
     */
    @Override
    public int selMaxSid(Integer u_id) {
        return sitemapper.selMaxSid(u_id);
    }
    /**
     * 修改后产生默认地址
     * @param u_id
     * @param s_id
     * @return
     */
    @Override
    public int updateSiteDFT(int u_id, int s_id) {
        return sitemapper.updateSiteDFT(u_id,s_id);
    }

    /**
     * 添加site表信息
     * @param u_id
     * @param u_name
     * @param s_region
     * @param s_d_address
     * @return
     */
    @Override
    public int insertSite(int u_id, String u_name, String s_region, String s_d_address) {
        return sitemapper.insertSite(u_id,u_name,s_region,s_d_address);
    }
    /**
     * 更新用户表的邮箱和电话
     * @param u_email
     * @param u_phone
     * @param u_id
     * @return
     */
    @Override
    public int setEmailPhone(String u_email, String u_phone, int u_id) {
        return sitemapper.setEmailPhone(u_email,u_phone,u_id);
    }

    /**
     * 查询该用户的默认收货地址
     * @return
     */
    @Override
    public Map<String, String> selSiteDft(int u_id) {
        return sitemapper.selSiteDft(u_id);
    }
}
