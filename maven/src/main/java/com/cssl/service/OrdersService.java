package com.cssl.service;

import com.cssl.pojo.Orders;
import com.github.pagehelper.Page;

public interface OrdersService {
    /**
     * 插入订单
     * @param o_number
     * @param u_id
     * @param o_payment
     * @param
     * @return
     */
    public int insertOrders(String o_number,int u_id,int o_payment,double o_totalprice);

    /**
     * 插入订单详情
     * @param o_number
     * @param cy_id
     * @param od_pirce
     * @param od_quantity
     * @return
     */
    public int insertOrderDetails(String o_number,int cy_id,double od_pirce,int od_quantity);

    /**
     * 查询用户订单
     * @param u_id 用户id
     * @param o_type 订单状态
     * @return
     */
    public Page<Orders> getorders(int u_id, int pageno, Integer o_type);

    /**
     * 查询未付款的订单总数
     * @return
     */
    public int selobligation();

    /**
     * 删除一个订单
     * @param number 订单编号
     * @return
     */
    public int delOrder(String number);

    /**
     * 查询用户的一个订单
     * @param o_number 订单编号
     * @param u_id 用户id
     * @return
     */
    public Orders selOrder(String o_number,int u_id);

    /**
     * 查询用户的一个订单
     * @param o_number 订单编号
     * @return
     */
    public Orders seloneorder(String o_number);

    /**
     * 取消订单
     * @param o_type 订单状态
     * @param o_number 订单号
     * @return
     */
    public int updaOrder(int o_type,String o_number);

    /**
     * 查询用户订单是否存在
     * @param number 订单号
     * @param id 用户id
     * @return
     */
    public int SelIsOrders(String number,int id);

    /**
     * 取消订单和退货时查询一个订单中订单详情的数量
     * @param o_number
     * @return
     */
    public Orders selorder(String o_number);
}
