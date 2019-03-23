package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.cssl.pojo.News;
import com.cssl.service.NewsService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;
    //查询新闻
    @RequestMapping("/selectNews")
    public String selectAllNews(@RequestParam(defaultValue = "1",value ="pageNum") Integer pageNum, HttpSession session){
        Page page= PageHelper.startPage(pageNum,4);
        List<News> newsList = newsService.selectAll();
        session.setAttribute("map",newsList);
        session.setAttribute("page",page);
        return "new";
    }
    /**
     * 返回到首页的
     * @param
     * @param session
     * @return
     */
    @RequestMapping("/selectNews1")
    public String selectAllNews1( HttpSession session){
        List<News> newsList = newsService.selectAll();
        session.setAttribute("mapNew",newsList);
        return "forward:/flashSale";//discountController
    }
    //模糊查询新闻
    @RequestMapping("/selectByName")
    @ResponseBody
    public String selectByName(@RequestParam(defaultValue = "1",value ="pageNum") Integer pageNum, Integer pageno, Model mod, HttpSession session, String vaa){
        Page page=PageHelper.startPage(pageNum,4);
        List<News> selectByName = newsService.selectByName(vaa);
        String json= JSON.toJSONString(selectByName);
        return json;

    }
}
