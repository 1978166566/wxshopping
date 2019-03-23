package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 商品图片表
 */
@Getter
@Setter
public class Product implements Serializable {
    private int p_id;//图片id
    private int  cs_id;//商品详情id
    private String p_color;//商品颜色
    private String p_image;//商品图片
    private Cdetails cdetails;
}
