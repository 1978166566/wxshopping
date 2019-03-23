package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单表
 */
@Getter
@Setter
public class Orders implements Serializable {
    private String o_number;//订单号
    private  int u_id;//订单人id
    private Date  o_time;//订单时间
    private  int o_type;//订单状态
    private int o_payment;//支付方式：是否为货到付款1：是2：不是
    private double o_totalprice;//总价
    private List<Map<String,Object>> list;//订单详情
}
