package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 *  用户信息
 */
@Getter
@Setter
public class Users implements Serializable {
    private int u_id;//用户id
    private String u_name;//用户名唯一
    private String u_pwd;//登陆密码
    private String u_phone;//手机号码
    private String u_pay_pwd;//支付密码
    private Date u_record_date;//注册时间
    private String u_type;//用户类型1：普通会员，2：超级管理员
    private String u_sex;
    private String u_email;
    private String u_address;
    private String u_remark;
}
