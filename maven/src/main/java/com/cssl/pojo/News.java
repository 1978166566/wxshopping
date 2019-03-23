package com.cssl.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.Parser;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城快讯表
 */
@Getter
@Setter
public class News implements Serializable {
    private int n_id;//新闻id
    private String n_title;//标题
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    public Date getN_time() {
        return n_time;
    }

    /* @DateTimeFormat(pattern = "yyyy-MM-dd")*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date  n_time;//发布时间
    private String n_content;//内容

}
