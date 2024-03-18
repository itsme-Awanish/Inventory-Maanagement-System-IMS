package com.ims.inventory_management_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ims.inventory_management_system.service.CategoryService;
import com.ims.inventory_management_system.service.ProductService;
import com.ims.inventory_management_system.model.Product;
import com.ims.inventory_management_system.model.Category;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService service;
    @Autowired
    CategoryService catService;

    @RequestMapping("/product")
    public ModelAndView product(){
        ModelAndView mv=new ModelAndView();
        List<Product> pro = service.getdata();
        mv.addObject("product",pro);
        mv.setViewName("product");
        return mv;
    }

    @PostMapping("/actionProduct")
    public ModelAndView handleProductAction(String action){
        ModelAndView mv = new ModelAndView();
        List<Product> pro = service.getdata();
        List<Category> cat = catService.getdata();
        mv.addObject("product",pro);
        mv.addObject("category",cat);
        if("Add Product".equals(action)){
            mv.setViewName("addproduct");
        }
        else if("Update Product".equals(action)){
            mv.setViewName("updateproduct");
        }
        else if("Delete Product".equals(action)){
            mv.setViewName("deleteproduct");
        }
        else{
            mv.setViewName("product");
        }
        return mv;
    }

    @RequestMapping("/addproduct")
    public ModelAndView addProduct(Product pro){
        ModelAndView mv =new ModelAndView();
        pro.setProId(service.getMaxProId());
        service.sendData(pro);
        mv.setViewName("redirect:/product");
        return mv;
    }

    @RequestMapping("/updateproduct")
    public ModelAndView updateProduct(Product pro){
        ModelAndView mv = new ModelAndView();
        service.updateProduct(pro);
        mv.setViewName("redirect:/product");
        return mv;
    }

    @RequestMapping("/deletemapping")
    public ModelAndView deleteProduct(int id){
        ModelAndView mv = new ModelAndView();
        service.deleteProduct(id);
        mv.setViewName("redirect:/product");
        return mv;
    }

}