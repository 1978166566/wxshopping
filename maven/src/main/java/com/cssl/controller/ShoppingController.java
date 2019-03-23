package com.cssl.controller;

import com.alibaba.fastjson.JSONArray;
import com.cssl.pojo.Shopping;
import com.cssl.pojo.Users;
import com.cssl.service.ShoppingService;
import com.cssl.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ShoppingController {
    @Autowired
    private ShoppingService shoppingService;

    /**
     * 立即购买
     *
     * @return
     */
    @RequestMapping("/instantShopping")
    public String shopping(@RequestParam(value = "proNum", required = false) int proNum, @RequestParam(value = "proId", required = false) int proId, HttpSession session, Model model) {
        try {
            Users users = (Users) session.getAttribute("getUser");
            if (users == null) {
                //用户没有登录;
                //               跳转到登录页面(数据不保存)
                return "/error";
                /*System.out.println("用户没有登陆 ");*/
            } else {
                // 用户登录了;
                //               将数据存储到购物车(跳转到支付页面)
                /* System.out.println("用户名"+users.getU_name());*/
                Shopping shopping = new Shopping();
                shopping.setU_id(users.getU_id());
                shopping.setCy_id(proId);
                shopping.setSp_quantity(proNum);
                List<Shopping> list1 = shoppingService.selShopping(users.getU_id());//查询数据库中的该用户的购物车对象
                boolean flag = true;
                for (Shopping shopping1 : list1) {
                    if (shopping1.getCy_id() == shopping.getCy_id()) {
                        flag = false;
                        int num = shoppingService.upShoppingNum(shopping1.getSp_quantity() + shopping.getSp_quantity(), shopping1.getSp_id());
                        if (num > 0) {
                            return "forward:/account2?cy_id="+proId+"&sp_quantity="+proNum;//立即购买成功
                        } else {
                            model.addAttribute("aa",4);
                            return "404";//立即购买失败
                        }
                    }
                }
                if (flag) {
                    int num = shoppingService.instShopping(shopping);
                    if (num > 0) {
                        return "forward:/account2?cy_id="+proId+"&sp_quantity="+proNum;//立即购买成功
                    } else {
                        model.addAttribute("aa",4);
                        return "404";//立即购买失败
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("加入购物车失败");
            model.addAttribute("aa", 1);//保存异常信息
            return "/404";
        }
        model.addAttribute("aa", 2);//保存异常信息
        return "/404";
    }

    /**
     * 加入购物车
     *
     * @param proNum  商品数量
     * @param proId   商品ID
     * @param session session
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/joinshopping")
    public String joinShopping(@RequestParam(value = "proNum", required = false) int proNum, @RequestParam(value = "proId", required = false) int proId, HttpSession session, HttpServletResponse response,
                               HttpServletRequest request) {
        try {
            Users users = (Users) session.getAttribute("getUser");//获取登录用户信息
            Shopping shopping = new Shopping();
            shopping.setCy_id(proId);
            shopping.setSp_quantity(proNum);
            if (users == null) {
                // 用户没有登录
                // 将数据存储到cookie;
                Cookie[] cookies = request.getCookies();//获取cookie
                String value = null;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("shopping")) {
                            value = cookie.getValue();//获取存储的数据
                        }
                    }
                }
                if (value == null) {
                    //第一次存储数据
                    List<Shopping> newlist = new ArrayList<Shopping>();
                    newlist.add(shopping);
                    String result = URLEncoder.encode(JSONArray.toJSONString(newlist), "utf-8");
                    Cookie cookie = new Cookie("shopping", result);
                    cookie.setMaxAge(60*60);
                    response.addCookie(cookie);
                } else {
                    //再次存储数据
                    value = URLDecoder.decode(value, "utf-8");
                    List<Shopping> list = JSONArray.parseArray(value, Shopping.class);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getCy_id() == proId) {
                            //修改数量
                            list.get(i).setSp_quantity(list.get(i).getSp_quantity() + proNum);
                            String result2 = URLEncoder.encode(JSONArray.toJSONString(list), "utf-8");
                            Cookie cookie = new Cookie("shopping", result2);
                            cookie.setMaxAge(60*60);
                            response.addCookie(cookie);
                            return "success";
                        }
                    }
                    //新添加购物车到cookie
                    list.add(shopping);
                    String result3 = URLEncoder.encode(JSONArray.toJSONString(list), "utf-8");
                    Cookie cookie = new Cookie("shopping", result3);
                    response.addCookie(cookie);
                }
                return "success";//添加购物车成功
            } else {
                // 用户登录了;
                // 将数据存储到购物车
                System.out.println("加入购物车用户登录了");
                shopping.setU_id(users.getU_id());//加入用户ID
                List<Shopping> list1 = shoppingService.selShopping(users.getU_id());//查询数据库中的该用户的购物车对象
                boolean flag = true;
                for (Shopping shopping1 : list1) {
                    if (shopping1.getCy_id() == shopping.getCy_id()) {
                        flag = false;
                        int num = shoppingService.upShoppingNum(shopping1.getSp_quantity() + shopping.getSp_quantity(), shopping1.getSp_id());
                        if (num > 0) {
                            return "success";//添加购物车成功
                        } else {
                            return "fail";//添加购物车失败
                        }
                    }
                }
                if (flag) {
                    int num = shoppingService.instShopping(shopping);
                    if (num > 0) {
                        return "success";//添加购物车成功
                    } else {
                        return "fail";//添加购物车失败
                    }
                }
            }
            return "404";
        } catch (Exception exception) {
            return "404";//加入购物车异常
        }

    }
    /**
     * 查询指定用户下的购物车商品
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selShopCount")
    public int selShoppingCount(HttpSession session,HttpServletRequest request) {
        Users users = (Users) session.getAttribute("getUser");
        int count=0;
        String value=null;
        if (users == null) {
               //用户没有登录.查询cookie中的数据
            Cookie[] cookies= request.getCookies();//获取cookie数组
            if(cookies!=null){
                for(Cookie cookie: cookies){
                    if(cookie.getName().equals("shopping")){
                        value= cookie.getValue();//获取存储的数据
                    }
                }
            }
            try {
                if(value!=null){
                    value=URLDecoder.decode(value,"utf-8");
                    List<Shopping> list=JSONArray.parseArray(value,Shopping.class);
                    for(Shopping shopping: list){
                         count+=shopping.getSp_quantity();
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
               //用户登录查询数据库shopping表中的数据
               List<Shopping> list=shoppingService.selShopping(users.getU_id());//获取数据库中的shopping
               session.setAttribute("shoppingCount",list);//将shopping对象存储在session
               for (Shopping as: list){
                  count+=as.getSp_quantity();//获取所有商品的数量
               }
        }
        return count;
    }

    /**
     * 查询该用户下的购物车详情
     * @param
     * @return
     */
    @RequestMapping("/selShoppingInfo")
    public String selShoppingInfo1(Model model,HttpSession session,HttpServletRequest request){
        Users users=(Users)session.getAttribute("getUser");
        if(users==null){
            //查询缓存shopping
            String value=null;
            Cookie[] cookies= request.getCookies();//获取cookie数组
            if(cookies!=null){
                for(Cookie cookie: cookies){
                    if(cookie.getName().equals("shopping")){
                        value= cookie.getValue();//获取存储的数据
                    }
                }
            }
            try {
                if(value!=null){
                    value=URLDecoder.decode(value,"utf-8");
                    List<Shopping> list=JSONArray.parseArray(value,Shopping.class);
                    List<Map<String,String>> newlist=new ArrayList<Map<String,String>>();
                    for (int i=0;i<list.size();i++){
                        Map<String,String> map=new HashMap<String,String>();
                        List<Map<String,String>> childlist=shoppingService.selProByID(list.get(i).getCy_id());//获取到指定的商品信息
                        System.out.println("??????????????"+childlist);
                        map.put("cy_introduction",childlist.get(i).get("cy_introduction"));
                        map.put("cy_name",childlist.get(i).get("cy_name"));
                        map.put("cy_price",childlist.get(i).get("cy_price"));
                        map.put("p_image",childlist.get(i).get("p_image"));
                        map.put("cy_id",String.valueOf(list.get(i).getCy_id()));
                        map.put("sp_id","0");
                        map.put("sp_quantity",String.valueOf(list.get(i).getSp_quantity()));
                        newlist.add(map);
                    }
                    model.addAttribute("shoppinginfo",newlist);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                model.addAttribute("aa",2);
                return "404";
            }
            return "/d-shopping";
        }else{
            //查询数据库shopping
            model.addAttribute("shoppinginfo",shoppingService.selShoppingInfo(users.getU_id()));
            return "/d-shopping";
        }

    }

    /**
     * 删除购物车
     * @return
     */
    @ResponseBody
    @RequestMapping("/delShopping")
   public String delShopping(@RequestParam(value = "args[]") List args,HttpSession session,HttpServletRequest request,HttpServletResponse response){
        Users users=(Users)session.getAttribute("getUser");
        if(users==null){
            //删除cookie
            String value=null;
            Cookie[] cookies= request.getCookies();//获取cookie数组
            if(cookies!=null){
                for(Cookie cookie: cookies){
                    if(cookie.getName().equals("shopping")){
                        value= cookie.getValue();//获取存储的数据
                    }
                }
            }
            try {
                value = URLDecoder.decode(value, "utf-8");//解码
                List<Shopping> list = JSONArray.parseArray(value, Shopping.class);//转换对象
                for (int i=0;i<args.size();i++){
                    for (int ii = 0; ii < list.size(); ii++) {
                        if (list.get(ii).getCy_id() ==Integer.valueOf(args.get(i).toString())) {
                            list.remove(ii);//删除操作
                            ii--;
                        }
                    }
                }
                String result2 = URLEncoder.encode(JSONArray.toJSONString(list), "utf-8");//重新编码
                Cookie cookie = new Cookie("shopping", result2);//创建cookie
                response.addCookie(cookie);//存储cookie
                return "y";
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "n";
            }

        }else{
            //删除databases
            boolean flag=false;
            for (int i=0;i<args.size();i++){
              int num=  shoppingService.delShoppingById(Integer.valueOf(String.valueOf(args.get(i))));
              if(num<1){
                  flag=true;
                  break;
              }
            }
            if(flag){
                return "n";
            }
            return "y";
        }
   }

    /**
     * 购物车商品数量添加和减少
     * @param proNum
     * @param proId
     * @return
     */
    @Autowired
    private SiteService siteService;
    @RequestMapping("/addMinus")
    @ResponseBody
    public String addAndMinus(int proNum,int proId,HttpSession session,HttpServletResponse response,HttpServletRequest request){
        Users users= (Users) session.getAttribute("getUser");
        if(users==null){
            //用户没有登陆。操作cookie
            Cookie[] cookies=request.getCookies();
            String value=null;
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("shopping")){
                    value=cookie.getValue();//获取cookie中的购物车字符串
                }
            }
            try {
                if(value!=null) {
                    value=URLDecoder.decode(value, "utf-8");
                    List<Shopping> list=JSONArray.parseArray(value,Shopping.class);
                    for(Shopping shopping: list){
                        if(shopping.getCy_id()==proId){
                            shopping.setSp_quantity(proNum);//修改cookie中的值
                        }
                    }
                    String result2 = URLEncoder.encode(JSONArray.toJSONString(list), "utf-8");//list转码
                    Cookie cookie = new Cookie("shopping", result2);//覆盖cookie
                    response.addCookie(cookie);//写入cookie
                }
                return "y";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                   return "n";
                }
            }else{
                //用户登陆，操作数据库
                int num=shoppingService.upShoppingNum(proNum,proId);
                if(num>0){
                    return "y";
                }else{
                    return "n";
                }
            }

        }
    /**
     * 去结算
     * @return
     */
     @RequestMapping("/account")
      public String account(@RequestParam(value="list") List list,HttpSession session,Model model){
         //筛选出该用户下的选中购买的购物车商品
        Users users=(Users) session.getAttribute("getUser");
        List<Map<String,String>> splist=shoppingService.selShoppingInfo(users.getU_id());
         List<Map<String,String>> newsplist=new ArrayList<Map<String,String>>();
         String pro_name="";
        for(int i=0;i<list.size();i++){
                for(int j=0;j<splist.size();j++){
                         if(String.valueOf(splist.get(j).get("sp_id")).equals(list.get(i))){
                             pro_name+=splist.get(j).get("cy_name")+" , ";
                             newsplist.add(splist.get(j));
                         }
                }
        }
         session.setAttribute("sp_list",newsplist);//保存需要购买的购物车对象
         session.setAttribute("pro_name",pro_name.substring(0,pro_name.length()-2));//保存需要购买的商品名
         Map<String,String> siteMap= siteService.selSiteDft(users.getU_id());//获取该用户的默认地址
         model.addAttribute("dftSite",siteMap);//保存默认地址
        return "order.html";
      }
    /**
     * 登录购买
     * @return
     */
    @RequestMapping("/account2")
    public String account2(int cy_id,int sp_quantity,HttpSession session,Model model){
        //筛选出该用户下的选中购买的购物车商品
        Users users=(Users) session.getAttribute("getUser");
        List<Map<String,String>> splist=shoppingService.selShoppingInfo(users.getU_id());
        String pro_name="";
        List<Map<String,String>> newsplist=new ArrayList<Map<String,String>>();
            for(int j=0;j<splist.size();j++){
                System.out.println(String.valueOf(splist.get(j).get("cy_id")));
                System.out.println(cy_id);
                if(String.valueOf(splist.get(j).get("cy_id")).equals(String.valueOf(cy_id))){
                    //改变数量
                    splist.get(j).remove("sp_quantity");
                    splist.get(j).put("sp_quantity",String.valueOf(sp_quantity));
                    pro_name+=splist.get(j).get("cy_name")+" , ";
                    newsplist.add(splist.get(j));
                }
            }
        session.setAttribute("sp_list",newsplist);//保存需要购买的购物车对象
        session.setAttribute("pro_name",pro_name.substring(0,pro_name.length()-2));//保存需要购买的商品名
        Map<String,String> siteMap= siteService.selSiteDft(users.getU_id());//获取该用户的默认地址、/
        model.addAttribute("dftSite",siteMap);//保存默认地址
        return "order.html";
    }

}
