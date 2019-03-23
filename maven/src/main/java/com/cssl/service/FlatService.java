package com.cssl.service;

import com.cssl.pojo.Flat;

import java.util.List;

public interface FlatService {
    /**
     *获取一级分类下的所有品牌
     * @return
     */
    public List<Flat> selBand(Integer oc_id);
}
