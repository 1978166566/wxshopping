package com.cssl.service;

import com.cssl.pojo.Shopping;
import com.cssl.pojo.Users;

import java.util.List;
import java.util.Map;

public interface ShoppingService {
    /**
     * 插入购物车
     * @param shopping
     * @return
     */
    public int instShopping(Shopping shopping);
    /**
     *查询指定用户下的商品
     * @param u_id
     * @return
     */
    public List<Shopping> selShopping(int u_id);
    /**
     * 更改购物车商品数量
     * @param sp_quantity
     * @param sp_id
     * @return
     */
    public int upShoppingNum(int sp_quantity,int sp_id);
    /**
     * 查询该用户下的购物车详情
     * @param u_id
     * @return
     */
    public List<Map<String,String>> selShoppingInfo(int u_id);
    /**
     * 根据cookie购物车商品id查询商品信息
     * @param u_id
     * @return
     */
    public List<Map<String,String>> selProByID(Integer u_id);
    /*
     *根据购物车id删除购物车记录
     * @param cy_id
     * @param u_id
     * @return
     */
    public int delShoppingById(int sp_id);
    /**
     * 查询某用户下的单个商品的数量
     * @param u_id
     * @param cy_id
     * @return
     */
    public int selProSum(int u_id,int cy_id);
}
