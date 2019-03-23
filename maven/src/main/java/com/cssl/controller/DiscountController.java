package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.cssl.service.DiscountProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class DiscountController {
    @Autowired
    private DiscountProductService discountProductService;

    /**
     * 限时抢购的信息,根据打折的折扣来排序
     * @param session
     * @return
     */
    @RequestMapping("/flashSale")
    public String flashSale(HttpSession session){
        List<Map<String, Object>> mapList = discountProductService.selectFlashSale();
        session.setAttribute("mapList",mapList);
        return "forward:/hostSale";
    }

    /**
     * 热卖 根据销量来排序
     * @param pageNo
     * @param session
     * @return
     */

    @RequestMapping("/hostSale")
    public String hostSale(Integer pageNo,HttpSession session){
        PageHelper.startPage(pageNo==null ?1:pageNo,6);
        List<Map<String, Object>> mapList1 = discountProductService.selectHostSale(pageNo);
        session.setAttribute("mapList2",mapList1);
        return "index";//页面
    }
    @ResponseBody
    @RequestMapping("/hostSaleJson")
    public String hostSale1(Integer pageNo,HttpSession session){
        PageHelper.startPage(pageNo==null ?1:pageNo,6);
        List<Map<String, Object>> mapList1 = discountProductService.selectHostSale(pageNo);
        String json=JSON.toJSONString(mapList1);
        return json;
    }


    /**
     * 根据id查询这个商品的详情信息
     * @param
     * @param session
     * @return
     */
    @RequestMapping("/discountProduct")
    public String commodity(HttpSession session,Integer cyid){
        Map<String,Object> commodity= discountProductService.selectCommdity(cyid);
        session.setAttribute("commodity",commodity);
        return "forward:selectCountDiscuss";
    }

    @RequestMapping("/selectCountDiscuss")
    public String selectCountDiscuss(HttpSession session,Integer cyid){
        Integer integerCountDiscuss = discountProductService.selectCountDiscuss(cyid);
        session.setAttribute("integerCountDiscuss",integerCountDiscuss);
        return "forward:selectCyInventory";
    }

    @RequestMapping("/selectCyInventory")
    public String selectCyInventory(HttpSession session,Integer cyid){
        Integer integerInventory = discountProductService.selectCyInventory(cyid);
        session.setAttribute("integerInventory",integerInventory);
        return "forward:buyPage";
    }
    /**
     * 根据版本号查询信息
     * @param
     * @param session
     * @param cyid
     * @return
     */
    @RequestMapping("/buyPage")
    public String commodity1(HttpSession session,Integer cyid){
        List<Map<String, Object>> selectBuyPage = discountProductService.selectBuyPage(cyid);
        session.setAttribute("selectBuyPage",selectBuyPage);
        return "forward:selectDiscuss";
    }
    @RequestMapping("/selectDiscuss")
    public String selectDiscuss(Integer cyid, Model model, HttpSession session){
        List<Map<String, Object>> selectDiscuss = discountProductService.selectDiscuss(cyid);
        session.setAttribute("cyid",cyid);
        model.addAttribute("selectDiscuss",selectDiscuss);
        return "page";
    }
    @RequestMapping("/selectHaoPing")
    @ResponseBody
    public String selectHaoPing(HttpSession session){
        Integer cyid=(Integer)session.getAttribute("cyid");
        List<Map<String, Object>> haoPing = discountProductService.selectHaoPing(cyid);
        session.setAttribute("haoPing",haoPing);
        String json= JSON.toJSONString(haoPing);
        return json;
    }

    @RequestMapping("/selectZhongPing")
    @ResponseBody
    public String selectZhongPing(HttpSession session){
        Integer cyid=(Integer)session.getAttribute("cyid");
        List<Map<String, Object>> zhongPing = discountProductService.selectZhongPing(cyid);
        session.setAttribute("zhongPing",zhongPing);
        String json=JSON.toJSONString(zhongPing);
        return json;
    }

    @RequestMapping("/selectChaPing")
    @ResponseBody
    public String selectChaPing(HttpSession session){
        Integer cyid=(Integer)session.getAttribute("cyid");
        List<Map<String, Object>> chaPing = discountProductService.selectChaPing(cyid);
        session.setAttribute("chaPing",chaPing);
        String json=JSON.toJSONString(chaPing);
        return json;
    }

}

