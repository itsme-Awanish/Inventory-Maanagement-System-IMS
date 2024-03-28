package com.ims.inventory_management_system.Controller;

import com.ims.inventory_management_system.model.Users;
import com.ims.inventory_management_system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class LoginController {
    public LoginService getService() {
        return service;
    }

    @Autowired
    public void setService(LoginService service) {
        this.service = service;
    }

    LoginService service;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/actionDashboard")
    public ModelAndView validate(Users u){
        ModelAndView mv = new ModelAndView();
        if (u.getUname().equals("admin") && u.getUpass().equals("admin")) {
            mv.setViewName("redirect:/user");
        } else if (service.isValid(u)) {
            mv.setViewName("redirect:/dashboard");
        }else {
            mv.addObject("response","Invalid user");
            mv.setViewName("login");
        }
        return mv;
    }

    @PostMapping("/actionmanage")
    public ModelAndView manage(String action){
        ModelAndView mv = new ModelAndView();
        if("Manage Category".equals(action)){
            mv.setViewName("redirect:/category");
        } else if ("Manage Product".equals(action)) {
            mv.setViewName("redirect:/product");
        } else if ("Place Order".equals(action)) {
            mv.setViewName("redirect:/order/");
        }else {
            mv.setViewName("dashboard");
        }
        return mv;
    }


}

