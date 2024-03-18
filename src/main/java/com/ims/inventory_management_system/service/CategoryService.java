package com.ims.inventory_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ims.inventory_management_system.repository.CategoryRepo;
import com.ims.inventory_management_system.model.Category;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepo repo;

    public CategoryRepo getRepo(){
        return repo;
    }

    @Autowired
    public void setRepo(CategoryRepo repo) {
        this.repo = repo;
    }

    public List<Category> getdata() {
        return repo.findAll();
    }

    public void sendData(Category cat){
        repo.save(cat);
    }
    public int getMaxCatId(){
        return (repo.getMaxCatId()+1);
    }

    public void updateCategory(Category cat) {
        repo.update(cat);
    }

    public boolean deleteCategory(int id){
        boolean result = false;
        try {
            repo.delete(id);
            result = true;
        } catch (Exception e) {

        }
        return result;
    }
}

