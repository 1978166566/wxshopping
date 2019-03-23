package com.cssl.serviceimpl;

import com.cssl.dao.NewsMapper;
import com.cssl.pojo.News;
import com.cssl.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceimpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> selectAll() {
        return newsMapper.selectAll();
    }

    @Override
    public List<News> selectByName(String keyword) {
        return newsMapper.selectByName(keyword);
    }
}
