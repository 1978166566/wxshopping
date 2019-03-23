package com.cssl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cssl.pojo.Orders;
import com.cssl.pojo.Site;
import com.cssl.pojo.Users;
import com.cssl.service.*;
import com.cssl.util.PageUtil;
import com.github.pagehelper.Page;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@Transactional
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private ShoppingService shoppingService;
    @RequestMapping("/addOrders")

    public String addOr(@RequestParam(value = "lists")List list, HttpSession session, Model model){
       Users users=(Users) session.getAttribute("getUser");
       String romdon=getRondom();
      int num= ordersService.insertOrders(romdon,users.getU_id(),Integer.valueOf(list.get(0).toString()),Integer.valueOf(list.get(5).toString()));
          if(num>0){
              List<Map<String,String>> list2= (List<Map<String,String>>)session.getAttribute("sp_list");//存放需要购买的商品
              for(int i=0;i<list2.size();i++){
                    int num2=ordersService.insertOrderDetails(romdon,Integer.valueOf(String.valueOf(list2.get(i).get("cy_id"))),Double.valueOf(String.valueOf(list2.get(i).get("cy_price"))),Integer.valueOf(String.valueOf(list2.get(i).get("sp_quantity"))));
                    if(num2>0){
                        //购物车有就修改
                        int sum=Integer.valueOf(String.valueOf(list2.get(i).get("sp_quantity")));
                        int pro1=Integer.valueOf(String.valueOf(list2.get(i).get("cy_id")));//立即购买的商品ID
                        int num4=shoppingService.selProSum(users.getU_id(),pro1);//查询出的数据库里的单个数量//二次产生订单会bug
                      //加入购物车里的数量大于需要购买的数量
                        if(num4 >sum){
                                shoppingService.upShoppingNum(num4-sum,Integer.valueOf(String.valueOf(list2.get(i).get("sp_id"))));
                        }else{
                            //购物车没有就删除
                            int num3=shoppingService.delShoppingById(Integer.valueOf(String.valueOf(list2.get(i).get("sp_id"))));
                        }
                        //修改库存
                        commodityService.updateCom(sum,pro1);
                    }
              }
              model.addAttribute("success",list);
              model.addAttribute("detaisNum",romdon);
              return "d-success.html";
          }
          model.addAttribute("aa",6);
         return "/404";
    }
    /**
     * 生成随机数
     * @return
     */
    public String getRondom(){
        String str="";
        String [] array= new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R",
                "S","T","U","V","W","X","Y","Z"};
        for(int i=0;i<3;i++){
            str+= array[new Random().nextInt(26)];
        }
        str += new SimpleDateFormat("yyMMddHHmm").format(new Date());
        str+=(new Random().nextInt(90000)+10000);
        return str;
    }
    @Autowired
    CommodityService commodityservice; //商品业务层对象用于修改商品的库存数量

    @Autowired
    OrdersDetailsService ordersdetailsservice; //订单详情用于删除订单详情

    @Autowired
    SiteService siteservice; //地址用于查询订单的地址信息

    /**
     * 查询订单
     *
     * @param id 用户id
     * @param o_type 订单状态
     * @return
     */
    @RequestMapping("/getorders")
    public String getorders(int id, Integer o_type, Integer pageno, Model model) {
        if (pageno == null) {
            pageno = 1;
        }
        if (o_type == null) {
            o_type = 0;
        }
        Page<Orders> order = ordersService.getorders(id, pageno, o_type); //查询订单信息
        //用户的订单信息
        int obligationcount = ordersService.selobligation(); //查询待付款的订单有多少个
        model.addAttribute("orders", order);
        model.addAttribute("obligationcount", obligationcount);
        model.addAttribute("pages", PageUtil.pages(order.getPages(), order.getPageNum()));
        model.addAttribute("pagenum",order.getPageNum());
        model.addAttribute("totalpage",order.getPages());
        System.out.println("当前页码"+order.getPageNum());
        return "my-d";
    }

    /**
     * 点击不同的订单状态时分页查询不同的订单状态
     * @param id 用户id
     * @param o_type 状态
     * @param pageno 当前页
     */
    @RequestMapping("/Selajax")
    @ResponseBody
    public String SelOrderAjax(int id, Integer o_type, Integer pageno, HttpSession session) throws IOException {
        System.out.println("当前页码"+pageno);
        if (pageno == null) {
            pageno = 1;
        }
        Page<Orders> order = ordersService.getorders(id, pageno, o_type); //查询订单信息
        if(order.size()==0)
        {
            return "n";
        }
        return JSON.toJSONString(order)+";"+order.getPages()+";"+JSON.toJSONString(PageUtil.pages(order.getPages(), order.getPageNum()));
    }

    /**
     * 判断订单是否存在
     * @param number 订单号
     * @param id 用户id
     * @return
     */
    @RequestMapping("/isnumber")
    @ResponseBody
    public int isnumber(String number,int id)
    {
        int isnumber=ordersService.SelIsOrders(number,id);
        if(isnumber>0)
        {
            return 1;
        }
        return 2;
    }

    /**
     * 查询用户的一个订单
     * @param number 订单号
     * @param id 用户id
     * @return
     */
    @RequestMapping("/SelOrder")
    @ResponseBody
    public String SelOrder(String number,int id)
    {
        Orders orders=ordersService.selOrder(number,id);
        return JSON.toJSONString(orders);
    }

    /**
     * 取消订单或者退货
     * @param o_number 订单号
     * @param o_type 状态
     * @return
     */
    @RequestMapping("/Quantity")
    @ResponseBody
    public String Quantity(String o_number,int o_type)
    {
        Orders orders=ordersService.selorder(o_number); //查询订单里面商品的数量
        int j = 0;
        int n=0;
        for (int i=0;i<orders.getList().size();i++)
        {
            n=commodityservice.updaquantity((int)orders.getList().get(i).get("OD_QUANTITY"),(int)orders.getList().get(i).get("CY_ID"));
            if(n>0)
            {
                ++j;
            }
        }
        if(j==orders.getList().size())
        {
            //修改商品库存数量成功修改状态
            int i=ordersService.updaOrder(o_type,o_number);
            if(i>0){
                return "success";
            }
        }
        return "no";
    }

    /**
     * 确认收货
     * @param o_number 订单号
     * @param o_type 类型
     * @return
     */
    @RequestMapping("/confirmreceipt ")
    @ResponseBody
    public String confirmreceipt(String o_number,int o_type)
    {
        int i=ordersService.updaOrder(o_type,o_number);
        if(i>0){
            return "success";
        }
        return "no";
    }

    /**
     * 删除订单
     * @param o_number 订单号
     * @return
     */
    @RequestMapping("/delorder")
    @ResponseBody
    public String delorder(String o_number)
    {
        int i=ordersdetailsservice.delOrdersDetails(o_number);
        int j=ordersService.delOrder(o_number);
        if(i>0&&j>0){
            return "success";
        }
        return "no";
    }

    /**
     * 查询一个订单
     * @param id 用户id
     * @param o_number 订单号
     * @return
     */
    @RequestMapping("/queryorder")
    public String queryOrder(String o_number,int id,Integer pageno,Model model)
    {
        if(pageno==null){
            pageno=1;
        }
        Orders orders=ordersService.seloneorder(o_number);
        Page<Map<String,Object>> page=ordersdetailsservice.seloneordersdetails(o_number,pageno);
        Site site=siteservice.selordersite(id);
        model.addAttribute("orders",orders);
        model.addAttribute("page",page);
        model.addAttribute("site",site);
        model.addAttribute("pages", PageUtil.pages(page.getPages(),page.getPageSize()));
        return "my-d-info";
    }

    /**
     * 查询一个订单的商品详情数量
     * @param o_number 订单号
     * @param pageno 当前页
     * @return
     */
    @RequestMapping("/AjaxQueryOrder")
    @ResponseBody
    public String AjaxQueryOrder(String o_number,Integer pageno)
    {
        Page<Map<String,Object>> page=ordersdetailsservice.seloneordersdetails(o_number,pageno);
        JSONArray jsonarray=new JSONArray();
        for(int i=0;i<page.size();i++)
        {
            JSONObject jsonobject=new JSONObject();
            jsonobject.put("PAGENO",page.getPageNum());
            jsonobject.put("OD_PRICE",page.get(i).get("OD_PIRCE"));
            jsonobject.put("OD_QUANTITY",page.get(i).get("OD_QUANTITY"));
            jsonobject.put("CY_ID",page.get(i).get("CY_ID"));
            jsonobject.put("CY_INTRODUCTION",page.get(i).get("CY_INTRODUCTION"));
            jsonobject.put("CY_PRICE",page.get(i).get("CY_PRICE"));
            jsonarray.add(jsonobject);
        }
        JSONObject jsonobject2=new JSONObject();
        jsonobject2.put("pages", JSON.toJSONString(PageUtil.pages(page.getPages(),page.getPageSize())));
        jsonarray.add(jsonobject2);
        return jsonarray.toString();
    }
}
