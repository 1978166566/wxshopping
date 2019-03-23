package com.cssl.controller;

import com.cssl.pojo.Users;
import com.cssl.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SiteController {

    @Autowired
    private SiteService siteService;
    @ResponseBody
    @RequestMapping("/addSite")
    public String add(@RequestParam(value = "lists")List list, HttpSession session){
        Users users=(Users) session.getAttribute("getUser");
        //清除该用户下的默认地址
         siteService.setDefaultZore(users.getU_id());
        //添加site表信息
            int num= siteService.insertSite(users.getU_id(),String.valueOf(list.get(0)),String.valueOf(list.get(1)),String.valueOf(list.get(2)));
           if(num>0){
               //更新用户表的邮箱和电话
               num=  siteService.setEmailPhone(String.valueOf(list.get(4)),String.valueOf(list.get(3)),users.getU_id());
               if(num>0){
                   return "y";
               }else{
                   return "n";
               }
        }
      return "n";
    }

    /**
     * 地址删除功能
     * @return
     */
    @ResponseBody
    @RequestMapping("/delSite")
   public String del(@RequestParam(value = "s_id",defaultValue="0",required = false) Integer s_id,HttpSession session){
        Users users= (Users)session.getAttribute("getUser");
        //删除地址
       int num1 = siteService.delSite(s_id);
        //帅选出最后添加的地址(最后一条此处会没有值)
        int countSite=siteService.selSitecount(users.getU_id());
        if(countSite<1){
            return "n";
        }else {
            Integer num = siteService.selMaxSid(users.getU_id());
            if (num > 0) {
                num = siteService.updateSiteDFT(users.getU_id(), num);
                if (num > 0) {
                    return "y";
                } else {
                    return "n";
                }
            } else {
                //最后一条值为零
                return "y";
            }
        }
   }
   @ResponseBody
   @RequestMapping("/updateSite")
   public String updateSite(@RequestParam(value = "lists",required =false) List list,HttpSession session){
        Users users= (Users) session.getAttribute("getUser");
        int num= siteService.updateSite(String.valueOf(list.get(0)),String.valueOf(list.get(1)),String.valueOf(list.get(2)),Integer.valueOf(list.get(5).toString()));
        if(num>0){
            //修改site表成功
            num=siteService.updateUser(String.valueOf(list.get(3)),String.valueOf(list.get(4)),users.getU_id());
            if(num>0){
            //修改用户表成功
                return "y";
            }else{
            //修改用户表失败
                return "n";
            }
        }else{
            //修改site表失败
            return "n";
        }
   }


}
