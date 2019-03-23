package com.cssl.service;

import com.cssl.pojo.News;

import java.util.List;

public interface NewsService {
    //查询所有的新闻 int pageSzie,int pageNo
    public List<News> selectAll();

    //根据关键字搜索
    public List<News> selectByName(String keyword);
}
