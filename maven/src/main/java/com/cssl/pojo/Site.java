package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 地址管理表
 */
@Getter
@Setter
public class Site implements Serializable {
    private int s_id;//地址id
    private int u_id;//用户id
    private String u_name;//用户真实姓名
    private int s_isempty;//是否是默认地址1：是默认2：不是
    private String s_region;//所在地区
    private String s_d_address;//收获详细地址
}
