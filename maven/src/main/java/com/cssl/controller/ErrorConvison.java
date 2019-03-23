package com.cssl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;

@Controller
public class ErrorConvison {
    @RequestMapping("/convison")
    public String convison(Model model){
        model.addAttribute("aa",3);//加入购物车异常
       return "/404";
    }
}
