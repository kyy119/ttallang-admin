package com.ttalang.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/branch/main")
    public String getBranch() {
        return "branch/main";
    }

    @GetMapping("/branch/map")
    public String getBranchMap() {
        return "branch/branchmap";
    }

    @GetMapping("/branch/addBranch")
    public String addBranch() {
        return "branch/addbranch";
    }

    @GetMapping("/branch/edit")
    public String editBranch() {
        return "branch/edit";
    }

    @GetMapping("/bicycle/main")
    public String bicycleMain() {
        return "bicycle/main";
    }

    @GetMapping("/bicycle/map")
    public String bicycleMap() {
        return "bicycle/bicyclemap";
    }

    @GetMapping("/bicycle/reported")
    public String bicycleReported() {
        return "bicycle/reported";
    }

    @GetMapping("/payment/main")
    public String paymentMain() {
        return "payment/main";
    }

    @GetMapping("/member/main")
    public String memberMain() {
        return "member/main";
    }

    @GetMapping("/bicycle/edit")
    public String bicycleEdit() {
        return "bicycle/edit";
    }
}
