package com.ims.inventory_management_system.service;

import com.ims.inventory_management_system.model.*;
import com.ims.inventory_management_system.repository.CustomerRepo;
import com.ims.inventory_management_system.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    private OrderRepo oRepo;

    private CustomerRepo cRepo;

    private ProductService pService;


    public OrderRepo getoRepo() {
        return oRepo;
    }


    public ProductService getpService() {
        return pService;
    }

    @Autowired
    public void setpService(ProductService pService) {
        this.pService = pService;
    }

    @Autowired
    public void setoRepo(OrderRepo oRepo) {
        this.oRepo = oRepo;
    }

    public CustomerRepo getcRepo() {
        return cRepo;
    }

    @Autowired
    public void setcRepo(CustomerRepo cRepo) {
        this.cRepo = cRepo;
    }


    public Cart getProductForCart(int proid, int quantity) {
        Product p = pService.getProductById(proid);
        Cart c = new Cart();
        c.setProid(proid);
        c.setProname(p.getProName());
        c.setQuantity(quantity);
        c.setPrice(p.getPrice()*quantity);
        c.setCategory(p.getCategory());
        return c;
    }

    public int getTotalCartAmount(List<Cart> cart) {
        int totalAmount=0;
        for (Cart c : cart) {
            totalAmount += c.getPrice();
        }
        return totalAmount;
    }

    public Customer getCustomerByPh_no(Long phno) {
        return cRepo.getCustomer(phno);
    }

    //This code is used to generate random String for order_id and Customer_id
    private String generateRandomString(String in) {
        // Characters to choose from
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Create a Random object
        Random random = new Random();

        // Create a StringBuilder to build the string
        StringBuilder sb = new StringBuilder(in);

        for (int i = 1; i < 12; i++) {
            // Generate a random alphanumeric character
            char randomChar = chars.charAt(random.nextInt(chars.length()));
            // Append the character to the string
            sb.append(randomChar);
        }
        return sb.toString();
    }

    private boolean saveCustomer(Customer c){
        return cRepo.save(c);
    }

    public Boolean saveAll(Reciept reciept) {
        Customer c = new Customer();
        c.setCid(generateRandomString("C"));
        c.setCname(reciept.getCname());
        c.setPhone_no(reciept.getPhone_no());
        return true;
    }
}



