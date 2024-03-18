package com.ims.inventory_management_system.service;

import com.ims.inventory_management_system.model.Users;
import com.ims.inventory_management_system.repository.UserRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public UserRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(UserRepo repo) {
        this.repo = repo;
    }

    private UserRepo repo;

    public List<Users> getdata(){
        return repo.findAll();
    }

    public void sendData(Users u){
        repo.save(u);
    }
    public int getMaxUid(){
        return (repo.getMaxUid()+1);
    }

    public Users find(int uid) {
        Users u1 = null;
        List<Users> u = repo.findAll();
        for(int i =0; i<u.size();i++){
            if(u.get(i).getUid() == uid){
                u1 = u.get(i);
            }
        }
        return u1;
    }

    public void update(Users u) {
        repo.updateData(u);
    }

    public Boolean Delete(int uid) {
        boolean isDeleted = true;
        if (find(uid) == null){
            isDeleted = false;
        }else {
            repo.deleteData(uid);
        }
        return isDeleted;
    }
}
