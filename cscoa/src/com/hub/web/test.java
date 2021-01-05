package com.hub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {
    @RequestMapping("/u/test")
    @ResponseBody
    public String test(@RequestBody Users user){
        System.out.println(user.toString());
        return "success";
    }
}
