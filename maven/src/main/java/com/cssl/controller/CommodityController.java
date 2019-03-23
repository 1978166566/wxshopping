package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.cssl.service.CommodityService;
import com.cssl.service.OneClassService;
import com.cssl.service.TowClassService;
import com.cssl.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    /**
     * 查询卖场推荐
     * @param pageNo
     * @return
     */
    @RequestMapping("/recommend")
    public String mall(@RequestParam(value = "pageNo",required =false,defaultValue = "1")int pageNo, HttpSession session){
        PageHelper.startPage(pageNo,4);
        List<Map<String,String>> list=commodityService.mall();
        session.setAttribute("mell",list);//保存卖场推荐
        System.out.println("加载了卖场推荐");
        return "forward:/getTowPro";
    }
    /**
     * 换一换异步加载卖场推荐
     * @param pageNo
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/recommend2")
    public String malls(@RequestParam int pageNo,HttpSession session){
        PageHelper.startPage(pageNo,4);
        List<Map<String,String>> list=commodityService.mall();
        session.setAttribute("mell",list);
        String page=JSON.toJSONString(list);
        return page;
    }
    /**
     * 查询二级分类下的部分商品
     * @param
     * @return
     */
    @RequestMapping("/getTowPro")
    public  String selTowPro(HttpSession session){
        List<Map<String,String>> lacelist=commodityService.selTowPro(16);
        List<Map<String,String>> makelist=commodityService.selTowPro(16);
        session.setAttribute("laceTowPro",lacelist);//服装二级商品加载
        session.setAttribute("makeTowPro",makelist);//美容化妆二级商品加载
        System.out.println("加载了二级分类下的商品");
        return "forward:/band";//flatController
    }
    /**
     * 异步加载二级分类下的商品
     */
    @ResponseBody
    @RequestMapping("/asynTowPro")
    public String asynTowPro(Integer oc_id){
        List<Map<String,String>> list=commodityService.selTowPro(oc_id);
        return JSON.toJSONString(list);//返回单个二级分类的商品
    }
    /**
     * 异步加载二级分类下的商品
     */
    @ResponseBody
    @RequestMapping("/asynTowPro2")
    public String asynTowPro1(Integer oc_id){
        List<Map<String,String>> list=commodityService.selTowPro(oc_id);
        return JSON.toJSONString(list);//返回单个二级分类的商品(2次)
    }


    @Autowired
    private OneClassService oneClassService; //一级分类
    @Autowired
    private TowClassService towClassService; //三级分类

    /**
     *
     * @param model
     * @param pageindex 查询商品信息时的当前页码
     * @param id 分类id
     * @param fix 判断为几级分类
     * @param classname 分类名
     * @param parentid 分类id的父级id
     * @return
     */
    @RequestMapping("/selcommodity")
    public String selcommodity(Model model,
                               Integer pageindex, Integer parentid,
                               Integer id, Integer fix,
                               String classname)
    {
        if(pageindex==null){
            pageindex=1;
        }
        Page<Map<String,Object>> commoditylist=commodityService.selcomm(fix,id,pageindex); //商品信息
        List<Map<String,Object>> classify; //分类集合
        if(fix==1)
        {
            classify=oneClassService.sellist();
            model.addAttribute("tc_content","1");
            //一级分类
        }else if(fix==2){
            classify=oneClassService.seltolist(parentid);
            model.addAttribute("tc_content", "2");
            //二级分类
        }else {
            //三级分类
            classify = towClassService.selthreelist(parentid);
            model.addAttribute("tc_content", "3");
        }
        model.addAttribute("commoditylist",commoditylist); //添加商品信息
        model.addAttribute("classify",classify); //添加分类信息
        model.addAttribute("parentid",parentid); //查询分类时要用到的分类父级id
        System.out.println(commoditylist.getPages());
        model.addAttribute("pages", PageUtil.pages(commoditylist.getPages(),commoditylist.getPageNum())); //根据自身分类id查询商品
        model.addAttribute("id",id);
        model.addAttribute("classname",classname);
        return "all-cl";
    }
}
