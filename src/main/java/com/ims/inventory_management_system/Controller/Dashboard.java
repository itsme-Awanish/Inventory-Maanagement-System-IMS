package com.ims.inventory_management_system.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Dashboard {

    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
