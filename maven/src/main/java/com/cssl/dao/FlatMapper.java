package com.cssl.dao;

import com.cssl.pojo.Flat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatMapper {
    /**
     *获取一级分类下的所有品牌
     * @return
     */
    public List<Flat> selBand(Integer oc_id);
}
