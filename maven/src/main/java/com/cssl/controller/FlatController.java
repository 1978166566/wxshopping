package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.cssl.pojo.Flat;
import com.cssl.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class FlatController {
    @Autowired
    private FlatService flatService;
    /**
     *加载一级分类下的所有品牌
     * @return
     */
    @RequestMapping("/band")
    public String selband(HttpSession session){
        List<Flat> list=flatService.selBand(18);
        List<Flat> list2=flatService.selBand(19);
        session.setAttribute("flats",list);//服装品牌
        session.setAttribute("flats2",list2);//美容品牌
        System.out.println("加载了一级分类下的品牌");
        return "forward:selectNews1";//newsController
    }
}
