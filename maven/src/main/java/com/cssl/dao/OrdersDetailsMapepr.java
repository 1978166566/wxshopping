package com.cssl.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface OrdersDetailsMapepr {
    /**
     * 删除订单详情
     * @param o_number
     * @return
     */
    public int delOrdersDetails(@Param("O_NUMBER") String o_number);

    /**
     * 查询一个订单下的订单详情
     * @param o_number 订单号
     * @return
     */
    public Page<Map<String,Object>> seloneordersdetails(@Param("o_number") String o_number, int pageno);
}
