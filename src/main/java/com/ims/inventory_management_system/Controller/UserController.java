package com.ims.inventory_management_system.Controller;

import com.ims.inventory_management_system.model.Users;
import com.ims.inventory_management_system.service.UserService;
import org.apache.catalina.User;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService service;
    @RequestMapping("/user")
    public ModelAndView user(){
        ModelAndView mv = new ModelAndView();
        List<Users> u = service.getdata();
        mv.addObject("users",u);
        mv.setViewName("user");
        return mv;
    }
    @PostMapping("/actionUser")
     public ModelAndView handelUserAction(String action){
        ModelAndView mv = new ModelAndView();
        List<Users> u = service.getdata();
        mv.addObject("users",u);
        if ("Add User".equals(action)){
            mv.setViewName("adduser");
        } else if ("Update User".equals(action)) {
            mv.setViewName("updateuser");
        } else if ("Delete User".equals(action)) {
            mv.setViewName("deluser");
        } else if ("Go to Dashboard".equals(action)) {
            mv.setViewName("dashboard");
        } else {
            mv.setViewName("user");
        }
        return mv;
    }
    @RequestMapping("/adduser")
    public ModelAndView addUser(Users u){
        ModelAndView mv = new ModelAndView();
        u.setUid(service.getMaxUid());
        service.sendData(u);
        mv.setViewName("redirect:/user");
        return mv;
    }
    @PostMapping("/finduser")
    public ModelAndView find(int uid){
        ModelAndView mv = new ModelAndView();
        Users singleUser= service.find(uid);
        List<Users> allUser = service.getdata();
        mv.addObject("users",allUser);
        String message="";
        Boolean isDisabled = true;
        if(singleUser != null){
            mv.addObject("singleUser", singleUser);
            message = "We found the User change the field You want to update ✅";
            isDisabled = false;
        }else {
            message = "User Not Found Verify the User id ! 😖";
        }
        mv.addObject("result", message);
        mv.addObject("isDisabled", isDisabled);
        mv.setViewName("updateuser");
        return mv;
    }
    @PostMapping("/updateuser")
    public ModelAndView updateUser(Users u){
        ModelAndView mv = new ModelAndView();
        service.update(u);
        mv.setViewName("redirect:/user");
        return mv;
    }
    @RequestMapping("/deleteuser")
    public ModelAndView deleteUser(int uid){
        Boolean isDeleted = service.Delete(uid);
        ModelAndView mv = new ModelAndView();
        List<Users> allUsers = service.getdata();
        mv.addObject("users",allUsers);
        if (!isDeleted){
            mv.addObject("result","The user you want to delete not found !");
            mv.setViewName("deluser");
        }else{
            mv.setViewName("redirect:/user");
        }
        return mv;
    }
}
