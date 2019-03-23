package com.cssl.serviceimpl;

import com.cssl.dao.OrdersDetailsMapepr;
import com.cssl.service.OrdersDetailsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrdersDetailsServiceimpl implements OrdersDetailsService {
    @Autowired
    OrdersDetailsMapepr ordersDetailsMapepr;
    @Override
    public int delOrdersDetails(String o_number) {
        return ordersDetailsMapepr.delOrdersDetails(o_number);
    }

    @Override
    public Page<Map<String, Object>> seloneordersdetails(String o_number, int pageno) {
        Page<Map<String, Object>> pages= PageHelper.startPage(pageno,1);
        ordersDetailsMapepr.seloneordersdetails(o_number,pageno);
        return pages;
    }
}
