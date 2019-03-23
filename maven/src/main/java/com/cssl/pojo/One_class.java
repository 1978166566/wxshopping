package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 分类信息表
 */
@Getter
@Setter
public class One_class implements Serializable {
    private int oc_id;//分类id
    private String oc_name;//分类名称
    private int oc_fid;//分类名称
    private List<One_class> stair;//二级分类集合
    private List<Tow_class> second;//三级分类集合
}
