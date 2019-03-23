package com.cssl.serviceimpl;


import com.cssl.dao.SolrMapper;
import com.cssl.pojo.User_collect;
import com.cssl.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class SolrServiceimpl implements SolrService {
    @Autowired
    private SolrMapper solrMapper;

    @Override
    public List<Map<String, Object>> selectFlat(Integer ocId) {
        return solrMapper.selectFlat(ocId);
    }

    @Override
    public List<Map<String, Object>> selectCsSize() {
        return solrMapper.selectCsSize();
    }

    @Override
    public List<Map<String, Object>> selectCsSystem() {
        return solrMapper.selectCsSystem();
    }

    @Override
    public List<Map<String, Object>> selectFlatProduct(String flatName) {
        return solrMapper.selectFlatProduct(flatName);
    }

    @Override
    public List<Map<String, Object>> selectProductSize(String size) {
        return solrMapper.selectProductSize(size);
    }

    @Override
    public List<Map<String, Object>> selectSystem(String system) {
        return solrMapper.selectSystem(system);
    }

    @Override
    public List<Map<String, Object>> selectSalesOrder() {
        return solrMapper.selectSalesOrder();
    }

    @Override
    public List<Map<String, Object>> selectPriceOrder() {
        return solrMapper.selectPriceOrder();
    }

    @Override
    public List<Map<String, Object>> selectTimeOrder() {
        return solrMapper.selectTimeOrder();
    }

    @Override
    public List<Map<String, Object>> selectInventory() {
        return solrMapper.selectInventory();
    }

    @Override
    public List<Map<String, Object>> selectDisCount() {
        return solrMapper.selectDisCount();
    }

    @Override
    public int insertCollect(User_collect user_collect) {
        return solrMapper.insertCollect(user_collect);
    }

    @Override
    public List<Map<String, Object>> selectSouSuo(Map<String,Object> map) {
        return solrMapper.selectSouSuo(map);
    }
}
