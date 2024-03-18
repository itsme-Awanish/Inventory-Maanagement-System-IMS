package com.ims.inventory_management_system.service;

import com.ims.inventory_management_system.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    public UserService getServiceUser() {
        return serviceUser;
    }

    @Autowired
    public void setServiceUser(UserService serviceUser) {
        this.serviceUser = serviceUser;
    }

    UserService serviceUser;

    public boolean isValid(Users u){
        boolean isValidUser = false;
        List<Users> allUser = serviceUser.getdata();
        for(int i = 0; i<allUser.size(); i++){
            Users user = allUser.get(i);
            if(user.getUname().equals(u.getUname()) && user.getUpass().equals(u.getUpass())){
                isValidUser = true;
            }
        }
        return isValidUser;
    }
}
