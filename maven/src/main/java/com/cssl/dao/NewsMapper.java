package com.cssl.dao;

import com.cssl.pojo.News;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface NewsMapper {
    //查询所有的新闻 int pageSzie,int pageNo
    public List<News> selectAll();

    //根据关键字搜索
    public List<News> selectByName(String keyword);
}
