package com.cssl.serviceimpl;

import com.cssl.dao.FlatMapper;
import com.cssl.pojo.Flat;
import com.cssl.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatServiceimpl implements FlatService {
    @Autowired
    private FlatMapper flatMapper;
    /**
     * 获取一级分类下的所有品牌
     * @return
     */
    @Override
    public List<Flat> selBand(Integer oc_id) {
        return flatMapper.selBand(oc_id);
    }
}
