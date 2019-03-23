package com.cssl.dao;

import com.cssl.pojo.User_collect;

import java.util.List;
import java.util.Map;

public interface SolrMapper {
    /**
     * 查询品牌
     */
    public List<Map<String,Object>> selectFlat(Integer ocId);

    /**
     * 查询尺寸
     */
    public List<Map<String,Object>> selectCsSize();

    /**
     * 查询系统
     * @return
     */
    public List<Map<String,Object>> selectCsSystem();

    /**
     * 根据品牌查找商品
     * @return
     */
    public List<Map<String,Object>> selectFlatProduct(String flatName);

    /**
     * 根据商品尺寸查找商品
     * @param size
     * @return
     */
    public List<Map<String,Object>> selectProductSize(String size);

    /**
     * 根据系统查找
     * @param system
     * @return
     */
    public List<Map<String,Object>> selectSystem(String system);

    /**
     * 根据销量排序
     * @return
     */
    public List<Map<String,Object>> selectSalesOrder();

    /**
     * 根据价格排序
     * @return
     */
    public List<Map<String,Object>> selectPriceOrder();

    /**
     * 根据商家时间排序
     * @return
     */
    public List<Map<String,Object>> selectTimeOrder();

    /**
     * 查找存货大于0的商品
     * @return
     */
    public List<Map<String,Object>> selectInventory();

    /**
     * 限时抢购的商品
     * @return
     */
    public List<Map<String,Object>> selectDisCount();

    /**
     * 添加一个商品对象
     * @return
     */
    public int insertCollect(User_collect user_collect);


    /**
     * 根据搜索框里面的东西查询
     * @return
     */
    public List<Map<String,Object>> selectSouSuo(Map<String,Object> map);
}
