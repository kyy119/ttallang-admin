package com.ttalang.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/searchTest")
    public String searchTest(){
        return "test/search";
    }
    @GetMapping("/kakaomap")
    public String kakakoMap(){
        return "test/map";
    }
}
