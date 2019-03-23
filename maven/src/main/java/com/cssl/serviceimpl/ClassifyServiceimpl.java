package com.cssl.serviceimpl;

import com.cssl.dao.ClassifyMapper;
import com.cssl.pojo.One_class;
import com.cssl.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassifyServiceimpl implements ClassifyService {
    @Autowired
    private ClassifyMapper classifyMapper;

    @Override
    public List<One_class> classify() {
        return classifyMapper.classify();
    }
}
