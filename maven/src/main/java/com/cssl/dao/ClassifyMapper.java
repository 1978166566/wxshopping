package com.cssl.dao;

import com.cssl.pojo.One_class;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ClassifyMapper {

    /**
     * 查询所有分类
     * @return
     */
    public List<One_class> classify();
}
