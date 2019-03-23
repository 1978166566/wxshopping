package com.cssl.serviceimpl;

import com.cssl.dao.OrdersMapper;
import com.cssl.pojo.Orders;
import com.cssl.service.OrdersService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceimpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    /**
     * 插入订单
     * @param o_number
     * @param u_id
     * @param o_payment
     * @param
     * @return
     */
    @Override
    public int insertOrders(String o_number, int u_id, int o_payment, double o_totalprice) {
        return ordersMapper.insertOrders(o_number,u_id,o_payment,o_totalprice);
    }
    /**
     * 插入订单详情
     * @param o_number
     * @param cy_id
     * @param od_pirce
     * @param od_quantity
     * @return
     */
    @Override
    public int insertOrderDetails(String o_number, int cy_id, double od_pirce, int od_quantity) {
        return ordersMapper.insertOrderDetails(o_number,cy_id,od_pirce,od_quantity);
    }

    @Override
    public Page<Orders> getorders(int u_id, int pageno, Integer o_type)
    {
        Page<Orders> page= PageHelper.startPage(pageno, 6);
        ordersMapper.getorders(u_id,pageno,o_type);
        return page;
    }

    @Override
    public int selobligation() {
        return ordersMapper.selobligation();
    }

    @Override
    public int delOrder(String number) {
        return ordersMapper.delOrder(number);
    }

    @Override
    public Orders selOrder(String o_number, int u_id) {
        return ordersMapper.selOrder(o_number,u_id);
    }

    @Override
    public Orders seloneorder(String o_number) {
        return ordersMapper.seloneorder(o_number);
    }

    @Override
    public int updaOrder(int o_type,String o_number) {
        return ordersMapper.updaOrder(o_type,o_number);
    }

    @Override
    public int SelIsOrders(String number, int id) {
        return ordersMapper.SelIsOrders(number,id);
    }

    @Override
    public Orders selorder(String o_number) {
        return ordersMapper.selorder(o_number);
    }


}
