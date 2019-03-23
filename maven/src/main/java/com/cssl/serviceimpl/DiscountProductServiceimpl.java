package com.cssl.serviceimpl;


import com.cssl.dao.DiscountProductMapper;
import com.cssl.service.DiscountProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DiscountProductServiceimpl implements DiscountProductService {
    @Autowired
    private DiscountProductMapper discountProductDao;

    @Override
    public List<Map<String, Object>> selectHostSale(Integer pageNo) {
        return discountProductDao.selectHostSale(pageNo);
    }

    @Override
    public List<Map<String, Object>> selectFlashSale() {
        return discountProductDao.selectFlashSale();
    }

    @Override
    public Map<String, Object> selectCommdity(Integer id) {
        return discountProductDao.selectCommdity(id);
    }

    @Override
    public Integer selectCountDiscuss(Integer cyid) {
        return discountProductDao.selectCountDiscuss(cyid);
    }

    @Override
    public Integer selectCyInventory(Integer cyid) {
        return discountProductDao.selectCyInventory(cyid);
    }

    @Override
    public List<Map<String, Object>> selectBuyPage(Integer cyid) {
        return discountProductDao.selectBuyPage(cyid);
    }

    @Override
    public Map<String, Object> selectBuyPage1(Integer cyid, Integer csversion) {
        return discountProductDao.selectBuyPage1(cyid,csversion);
    }

    @Override
    public List<Map<String, Object>> selectDiscuss(Integer cyid) {
        return discountProductDao.selectDiscuss(cyid);
    }

    @Override
    public List<Map<String, Object>> selectHaoPing(Integer cyid) {
        return discountProductDao.selectHaoPing(cyid);
    }

    @Override
    public List<Map<String, Object>> selectZhongPing(Integer cyid) {
        return discountProductDao.selectZhongPing(cyid);
    }

    @Override
    public List<Map<String, Object>> selectChaPing(Integer cyid) {
        return discountProductDao.selectChaPing(cyid);
    }
}
