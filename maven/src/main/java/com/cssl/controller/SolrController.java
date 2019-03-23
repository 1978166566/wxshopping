package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cssl.pojo.User_collect;
import com.cssl.pojo.kkk;
import com.cssl.service.SolrService;
import com.cssl.util.PageUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SolrController {
    @Autowired
    private SolrTemplate solrTemplate;
    @Autowired
    private SolrService solrService;
    @RequestMapping("/selectAll")
    public String selectAll(String cyname, HttpSession session, Long pageno) {
        Query query;
        if (cyname != null && cyname!="") {
             query=new SimpleQuery("*:*");
            Criteria cri = new Criteria("cy_name").contains(cyname);
            //条件追加
            query.addCriteria(cri);
            query.setOffset(0L);
            query.setRows(8);
        }else{
            query = new SimpleQuery("*:*");
            Criteria cri = new Criteria("cy_name").contains("小米");
            query.addCriteria(cri);
            query.setOffset(pageno);
            query.setRows(8);
        }
        ScoredPage<kkk> wx = solrTemplate.queryForPage("wx", query, kkk.class);
        ScoredPage<Map> test_core = solrTemplate.queryForPage("wx", query, Map.class);
        session.setAttribute("wx",wx);
        session.setAttribute("totalPage",wx.getTotalPages());
        session.setAttribute("pages", PageUtil.pages(wx.getTotalPages(),wx.getNumber()));
        session.setAttribute("testCore", test_core);


        /*----------------------------------------------------------------*/
        List<Map<String, Object>> mapList2 = solrService.selectFlat(18);
        List<Map<String, Object>> selectCsSize = solrService.selectCsSize();
        List<Map<String, Object>> selectCsSystem = solrService.selectCsSystem();
        session.setAttribute("mapList2",mapList2);
        session.setAttribute("selectCsSize",selectCsSize);
        session.setAttribute("selectCsSystem",selectCsSystem);
        return "all-class";
    }

//    @RequestMapping("/selectHigth")
//    public String selectHigth(String cyname,HttpSession session){
//       // Map<String,List<kkk>>map=new HashMap<>();
//        Criteria cri = new Criteria("cy_name").contains(cyname);
//        HighlightQuery query = new SimpleHighlightQuery(cri);  //query.addCriteria(cri);
//        HighlightOptions ho = new HighlightOptions();
//        ho.addField("cy_name");   //高亮域，可以多个
//        ho.setSimplePrefix("<em style='color:red'>");
//        ho.setSimplePostfix("</em>");
//        //为查询对象设置高亮选项
//        query.setHighlightOptions(ho);
//        //返回高亮页对象
//        HighlightPage<kkk> hp = solrTemplate.queryForHighlightPage("wx",query,kkk.class);
//        List<HighlightEntry<kkk>> list = hp.getHighlighted();
//        session.setAttribute("list",list);
//        for (HighlightEntry<kkk> entry : list){
//            //获取高亮域列表（高亮域的个数）
//            List<HighlightEntry.Highlight> hl = entry.getHighlights();
//            for (HighlightEntry.Highlight h : hl){
//                //每个域可以有多值
//                List<String> ls = h.getSnipplets();
//                System.out.println("ls:"+ls);
//            }
//            if(hl.size()>0 && hl.get(0).getSnipplets().size()>0){
//                kkk e = entry.getEntity();
//                //假设只一个高亮域
//                e.setCy_name(hl.get(0).getSnipplets().get(0));
//            }
//        }
//        //map.put("rows",hp.getContent());
//        return "all-class";
//    }

    @RequestMapping("/selectFlat")
    @ResponseBody
    public String selectFlat(String flatname){
        System.out.println(flatname);
        String kk=flatname.substring(0,2);
        PageHelper.startPage(1,8);
        List<Map<String, Object>> mapList2 = solrService.selectFlatProduct(kk);
        String json=JSON.toJSONString(mapList2);
        return json;
    }

    @RequestMapping("/selectProductSize")
    @ResponseBody
    public String selectProductSize(String scSize){
        System.out.println(scSize);
        PageHelper.startPage(1,8);
        List<Map<String, Object>> mapList2 = solrService.selectProductSize(scSize);
        String json=JSON.toJSONString(mapList2);
        return json;
    }

    @RequestMapping("/selectSystem")
    @ResponseBody
    public String selectSystem(String scSystem){
        String kk=scSystem.substring(0,2);
        PageHelper.startPage(1,8);
        List<Map<String, Object>> mapList2 = solrService.selectSystem(kk);
        String json=JSON.toJSONString(mapList2);
        return json;
    }

    /**
     * 按照销量
     * @param
     * @return
     */
    @RequestMapping("/selectSalesOrder")
    @ResponseBody
    public String selectSalesOrder(){
        PageHelper.startPage(1,8);
        List<Map<String, Object>> mapList2 = solrService.selectSalesOrder();
        String json= JSON.toJSONString(mapList2);
        return json;
    }
    /**
     * 根据价格排序
     * @return
     */
    @RequestMapping("/selectPriceOrder")
    @ResponseBody
    public String selectPriceOrder(){
        PageHelper.startPage(1,8);
        List<Map<String, Object>> mapList2 = solrService.selectPriceOrder();
        String json=JSON.toJSONString(mapList2);
        return json;
    }
    /**
     * 根据商家时间排序
     * @return
     */
    @RequestMapping("/selectTmeOrder")
    @ResponseBody
    public String selectTmeOrder(HttpSession session){
        PageHelper.startPage(1,8);
        List<Map<String, Object>> mapList2 = solrService.selectTimeOrder();
        String json=JSON.toJSONString(mapList2);
        return json;
    }

    /**
     * 查找存货大于0的商品
     * @return
     */
    @RequestMapping("selectInventory")
    @ResponseBody
    public String selectInventory(){
        PageHelper.startPage(1,8);
        List<Map<String, Object>> mapList2 = solrService.selectInventory();
        String json=JSON.toJSONString(mapList2);
        return json;
    }

    @RequestMapping("/insertCollect")
    @ResponseBody
    public String insertCollect(Integer cyid){
        PageHelper.startPage(1,8);
        User_collect user_collect=new User_collect();
        user_collect.setC_pid(cyid);
        user_collect.setUm_id(1);
        user_collect.setU_id(1);
        int i = solrService.insertCollect(user_collect);
        String json= JSON.toJSONString(i);
        return json;
    }

    /**
     * 根据搜索框里面的东西查询
     * @param cy_name
     * @param cy_price
     * @param cy_price1
     * @return
     */
    @RequestMapping("/selectSouSuo")
    @ResponseBody
    public JSON selectSouSuo(String cy_name,double cy_price,double cy_price1){
        PageHelper.startPage(1,8);
        System.out.println(cy_name);
        System.out.println(cy_price);
        System.out.println(cy_price1);
        Map<String,Object> map = new HashMap<>();
        map.put("cy_name",cy_name);
        map.put("cy_price",cy_price);
        map.put("cy_price1",cy_price1);
        PageHelper.startPage(1,8);
        List<Map<String, Object>> mapList2 = solrService.selectSouSuo(map);
        System.out.println("aaa"+mapList2.size());
        JSONObject json = new JSONObject();
        json.put("result",mapList2);
        /*json 传值的格式*/
//        String json=JSON.toJSONString(mapList2);
//        System.out.println(json);
        return json;
    }
}
