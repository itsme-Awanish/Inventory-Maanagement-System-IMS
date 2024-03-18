package com.ims.inventory_management_system.Controller;

import com.ims.inventory_management_system.model.*;
import com.ims.inventory_management_system.repository.CustomerRepo;
import com.ims.inventory_management_system.service.CategoryService;
import com.ims.inventory_management_system.service.OrderService;
import com.ims.inventory_management_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/order")
@SessionAttributes("Reciept")
public class OrderController {
    private OrderService oService; //order service object
    private CategoryService cService; //category service

    private ProductService pService;// Product service


    public ProductService getpService() {
        return pService;
    }

    @Autowired
    public void setpService(ProductService pService) {
        this.pService = pService;
    }

    public CategoryService getcService() {
        return cService;
    }

    @Autowired
    public void setcService(CategoryService cService) {
        this.cService = cService;
    }

    public OrderService getoService() {
        return oService;
    }

    @Autowired
    public void setoService(OrderService oService) {
        this.oService = oService;
    }
    @ModelAttribute("Reciept")
    //Creating the session object
    public Reciept obj(){
        return new Reciept();
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/order/start";
    }

    @GetMapping("/start")
    public ModelAndView viewPlaceOrder(){
        ModelAndView mv = new ModelAndView();
        List<Category> c = cService.getdata();
        mv.addObject("categories",c);
        mv.setViewName("order");
        return mv;
    }

    @GetMapping("/productByCategory")
    @ResponseBody
    public List<Product> getProductbyCategory(String category){
        return pService.getDataByCategory(category);
    }

    @PostMapping("/findCustomer")
    public ModelAndView findCustomer(@ModelAttribute("Reciept") Reciept reciept,@RequestParam(required = false, value = "phoneNumber") Long phno){
        Customer c;
        ModelAndView mv = new ModelAndView();
        c = oService.getCustomerByPh_no(phno);
        if (c != null){
            reciept.setPhone_no(phno);
            reciept.setCname(c.getCname());
            reciept.setFilled(true);
            reciept.setNOFilled(true);
        }else{
            reciept.setMessage("Customer is not found please add name");
            reciept.setPhone_no(phno);
            reciept.setNOFilled(true);
        }
        mv.setViewName("redirect:/order/start");
        return mv;
    }

    @PostMapping("/addToCart")
    public ModelAndView addToCart(@ModelAttribute("Reciept") Reciept reciept, @RequestParam(required = false, value = "customerName") String cname, @RequestParam(required = false,value = "phoneNumber") Long pno, @RequestParam(required = false,value = "product") Integer proid, @RequestParam(required = false,value = "quantity") Integer quantity){
        ModelAndView mv = new ModelAndView();
        if (!reciept.isFilled() && !cname.isEmpty() && reciept.isNOFilled()){
            reciept.setCname(cname);
            reciept.setFilled(true);
        }
        if (proid != null && quantity != null) {
            boolean add = reciept.getCart().add(oService.getProductForCart(proid, quantity));
            reciept.setTotalAmount(oService.getTotalCartAmount(reciept.getCart()));
            reciept.setMessage(null);
        }else {
            reciept.setMessage("Please add product");
        }
        mv.setViewName("redirect:/order/start");
        return mv;
    }


    @PostMapping("/clear")
    public String clear(@ModelAttribute("Reciept") Reciept reciept){
        reciept.setCname(null);
        reciept.setCart(new ArrayList<Cart>());
        reciept.setPhone_no(null);
        reciept.setFilled(false);
        reciept.setNOFilled(false);
        return "redirect:/order/start";
    }

    @GetMapping("/confirm")
    public String placeOrder(@ModelAttribute("Reciept")Reciept reciept){
        Boolean isSucessfull = oService.saveAll(reciept);

        return "redirect:/order/start";
    }



}
