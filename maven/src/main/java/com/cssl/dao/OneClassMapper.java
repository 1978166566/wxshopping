package com.cssl.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface OneClassMapper {

    /**
     * 查询所有的一级分类
     * @return
     */
    public List<Map<String,Object>> sellist();

    /**
     * 查询某一个一级分类下的所有二级分类
     * @return
     */
    public List<Map<String,Object>> seltolist(@Param("oc_id") int sid);
}
