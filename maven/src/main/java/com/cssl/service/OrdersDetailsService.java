package com.cssl.service;

import com.github.pagehelper.Page;

import java.util.Map;

public interface OrdersDetailsService {
    /**
     * 删除订单详情
     * @param o_number
     * @return
     */
    public int delOrdersDetails(String o_number);

    /**
     * 查询一个订单下的订单详情
     * @param o_number 订单号
     * @return
     */
    public Page<Map<String,Object>> seloneordersdetails(String o_number, int pageno);
}
