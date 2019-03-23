package com.cssl.controller;

import com.alibaba.fastjson.JSONArray;
import com.cssl.pojo.One_class;
import com.cssl.pojo.Shopping;
import com.cssl.pojo.Users;
import com.cssl.service.ClassifyService;
import com.cssl.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class InitController {
    @Autowired
    private ClassifyService classifyService;
    @Autowired
    private ShoppingService shoppingService;
    /**
     * 页面初始化,加载分类,检查购物车中是否有商品/有就添加到数据库,清空cookie.
     * @return
     */
    @RequestMapping("/init")
    public String classify(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        //登录之前拦截cookie.将cookie中的购物车对象,插入购物车表
        Cookie[] cookies=request.getCookies();//获取cookie
        String value=null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("shopping")){
                    value= cookie.getValue();//获取存储的数据
                }
            }
        }
        //获取用户对象
        Users users=(Users) session.getAttribute("getUser");
        //解码cookie中存储的购物车对象
        try {
            if(value!=null && users!=null){
                value= URLDecoder.decode(value,"utf-8");
                List<Shopping> list= JSONArray.parseArray(value,Shopping.class);///得到cookie中的对象
                for(int i=0;i<list.size();i++){
                    list.get(i).setU_id(users.getU_id());//为购物车赋值(用户id)
                }
                for(int i=0;i<list.size();i++){
                    boolean flag=false;
                    //将list中的购物车缓存添加到数据库
                    List<Shopping> list1=shoppingService.selShopping(users.getU_id());//查询数据库中的该用户的购物车对象
                    for(int j=0;j<list1.size();j++){
                        if(list1.get(j).getCy_id()==list.get(i).getCy_id()){
                            flag=true;
                            //数据库存在该商品.更改购物车商品的数量
                            int num=shoppingService.upShoppingNum(list1.get(j).getSp_quantity()+list.get(i).getSp_quantity(),list1.get(j).getSp_id());
                            if(num<1){
                                System.out.println("更新shopping数量异常.....initController");
                            }
                        }
                    }
                   if(!flag){
                       //购物车商品添加
                        int num = shoppingService.instShopping(list.get(i));
                       if(num<1){
                           System.out.println("将cookie中的购物车缓存添加到数据库出错....initSontroller");
                       }
                   }
                }
                //设置cookie失效
                if(cookies != null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals("shopping")){
                           cookie.setMaxAge(0);
                           response.addCookie(cookie);
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //在购买商品页面,加载购物车/cookie数量
        session.setAttribute("loadclass",classifyService.classify());//保存分类
        List<One_class> List=(List<One_class>)session.getAttribute("loadclass");
        /*for(int i=0;i<List.size();i++){
            System.out.println("一级"+List.get(i).getOc_name()
            );
            System.out.print("");
            for(int j=0;j< List.get(j).getStair().size();j++) {
                System.out.println("二级"+List.get(i).getStair().get(j).getOc_name());
               for(int k=0;k< List.get(i).getStair().get(j).getSecond().size();k++) {
                    System.out.println("三级"+List.get(i).getStair().get(j).getSecond().get(k).getTc_content());
                }
            }
        }*/
        System.out.println("加载了分类");
        return "forward:/recommend";//commodityController
    }

}
