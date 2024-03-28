package com.ims.inventory_management_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ims.inventory_management_system.service.CategoryService;
import com.ims.inventory_management_system.model.Category;
import java.util.List;

@Controller
public class CategoryController {

    public CategoryService getService() {
        return service;
    }

    @Autowired
    public void setService(CategoryService service) {
        this.service = service;
    }

    CategoryService service;

    @RequestMapping("/category")
    public ModelAndView category(String message){
        ModelAndView mv=new ModelAndView();
        List<Category> cat = service.getdata();
        mv.addObject("category",cat);
        mv.addObject("message",message);
        mv.setViewName("category");
        return mv;
    }

    @PostMapping("/actionCategory")
    public ModelAndView handleCategoryAction(String action){
        ModelAndView mv = new ModelAndView();
        List<Category> cat = service.getdata();
        mv.addObject("category",cat);
        if("Add Category".equals(action)){
            mv.setViewName("addcategory");
        }
        else if("Update Category".equals(action)){
            mv.setViewName("updatecategory");
        }
        else if("Delete Category".equals(action)){
            mv.setViewName("deletecategory");
        }
        else{
            mv.setViewName("redirect:/dashboard");
        }
        return mv;
    }

    @RequestMapping("/addCategory")
    public ModelAndView addCategory(Category cat){
        ModelAndView mv=new ModelAndView();
        cat.setCatId(service.getMaxCatId());
        service.sendData(cat);
        mv.setViewName("redirect:/category");
        return mv;
    }

    @RequestMapping("/updateCategory")
    public ModelAndView updateCategory(Category cat){
        ModelAndView mv=new ModelAndView();
        service.updateCategory(cat);
        mv.setViewName("redirect:/category");
        return mv;
    }

    @RequestMapping("/deleteCategory")
    public ModelAndView deleteCategory(int id){
        ModelAndView mv=new ModelAndView();
        String message = null;
        boolean isDeleted=false;
        isDeleted=service.deleteCategory(id);
        if (!isDeleted){
            message = "Category Id not found or Product listed in this Category !!";
        }
        mv.addObject("message",message);
        mv.setViewName("redirect:/category");
        return mv;
    }

}
