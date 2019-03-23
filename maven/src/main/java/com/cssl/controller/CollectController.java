package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.cssl.pojo.User_collect;
import com.cssl.pojo.Users;
import com.cssl.service.CollectService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 查询出这个用户出来 后面根据这个用户查询收藏的信息
     * @param session
     * @param uid
     * @return
     */
    @RequestMapping("/selectUserName")
    public String selectUserName(HttpSession session,Integer uid){
        Users users = collectService.selectUsers(uid);
        session.setAttribute("getUser",users);
        return "forward:selectShopId";
    }

    /**
     * 根据用户查找用户下面的收藏的店铺id
     * 并且根据用户id查找店铺的信息
     * @param
     * @return
     */
    @RequestMapping("/selectShopId")
    public String selectShowId(HttpSession session,Integer pageno){
        Users users = (Users) session.getAttribute("users");
        Page page= PageHelper.startPage(pageno==null ? 1 :pageno,4);
        List<User_collect> user_collects = collectService.selectAllShopProduct(1);
        session.setAttribute("user_collects",user_collects);
        session.setAttribute("page",page);
        return "my-s.html";
    }

    //ajax 点击收藏的商品 查询出来
    @RequestMapping("/collectThing")
    @ResponseBody
    public String selectCollectThing(HttpSession session, Integer pageNo){
        Users users = (Users) session.getAttribute("users");
        PageHelper.startPage(pageNo==null?1:pageNo,2);
        List<Map<String, Object>> mapList = collectService.collectThing(1);
        String json= JSON.toJSONString(mapList);
        return json;
    }

    /**
     * 搜索框 ,根据商品名称或者商品编号搜索的
     * @return
     */
    @RequestMapping("/selectBySouSuo")
    @ResponseBody
    public String selectBySouSuo(String cyName,HttpSession session,Integer pageno){
        Page page = PageHelper.startPage(pageno == null ? 1 : pageno, 2);
        List<Map<String, Object>> maps = collectService.selectBySouSuo(cyName);
        System.out.println(maps.size());
        String json=JSON.toJSONString(maps);
        return json;
    }
    /**
     *
     * @return
     */
    @RequestMapping("/insertCollects")
    @ResponseBody
    public String insertCollects(int ap,HttpSession session){
      Users users=(Users)session.getAttribute("getUser");
        int num=collectService.insertCollect(ap,users.getU_id());
        if(num>0){
            return "y";
        }else{
            return "n";
        }
    }
}
