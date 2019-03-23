package com.cssl.service;

import java.util.List;
import java.util.Map;

public interface DiscountProductService {
    //限时抢购的信息,根据打折的折扣来排序,分页查询
    public List<Map<String,Object>> selectFlashSale();

    //根据id查询商品信息
    public Map<String,Object> selectCommdity(Integer id);

    //根据id查询这个商品的评论
    public Integer selectCountDiscuss(Integer cyid);

    //根据id查询这个商品的库存
    public Integer selectCyInventory(Integer cyid);

    //热卖区的信息,根据销量多少来排序,分页查询
    public List<Map<String,Object>> selectHostSale(Integer pageNo);

    //查询版本问题
    public List<Map<String,Object>> selectBuyPage(Integer cyid);

    //根据版本查找不同的价格
    public Map<String,Object> selectBuyPage1(Integer cyid,Integer csversion);

    //根据商品id查询评论
    public List<Map<String,Object>> selectDiscuss(Integer cyid);

    public List<Map<String,Object>>selectHaoPing(Integer cyid);

    public List<Map<String,Object>>selectZhongPing(Integer cyid);

    public List<Map<String,Object>>selectChaPing(Integer cyid);
}
