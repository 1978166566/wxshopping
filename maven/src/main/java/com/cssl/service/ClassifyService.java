package com.cssl.service;

import com.cssl.pojo.One_class;

import java.util.List;
import java.util.Map;
public interface ClassifyService {
    /**
     * 查询所有分类
     * @return
     */
    public List<One_class> classify();
}
