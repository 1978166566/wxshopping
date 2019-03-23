package com.cssl.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TowClassMapper {
    /**
     * 查询某一个二级分类下的所有三级分类
     * @param towid
     * @return 二级分类的id
     */
    public List<Map<String,Object>> selthreelist(@Param("oc_id") int towid);
}
