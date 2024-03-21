package com.ims.inventory_management_system.service;

import com.ims.inventory_management_system.model.*;
import com.ims.inventory_management_system.repository.CustomerRepo;
import com.ims.inventory_management_system.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.Calendar;

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
    public String generateRandomString(String in) {
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
    public Date getCurrentSqlDate() {
        // Use the Calendar class to get the current time
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();

        // Convert java.util.Date to java.sql.Date
        Date sqlDate = new Date(currentDate.getTime());
        return sqlDate;
    }

    private void update_inventory(List<Cart> c){
        for (Cart crt : c) {
            Product p = new Product();
            p = pService.getProductById(crt.getProid());
            p.setQuantity(p.getQuantity()-crt.getQuantity());
            pService.updateProduct(p);
        }
    }

    private boolean saveCustomer(Customer c){
        return cRepo.save(c);
    }
    private void addOrder(Order o){
        oRepo.saveOrder(o);
    }

    public Boolean saveAll(Reciept reciept) {

        //save in customer
        Customer c = new Customer();
        if(!reciept.isCustFoundDb()){
            c.setCid(generateRandomString("C"));
            c.setCname(reciept.getCname());
            c.setPhone_no(reciept.getPhone_no());
            cRepo.save(c);
        }
        else{
            c = getCustomerByPh_no(reciept.getPhone_no());
        }

        //save in order
        Order o = new Order();
        o.setOid(reciept.getOid());
        o.setDop(getCurrentSqlDate());
        o.setCid(c.getCid());
        oRepo.saveOrder(o);

        // save in cart
        List<Cart> crt = reciept.getCart();
        oRepo.saveProduct(o.getOid(),crt);
        update_inventory(crt);
        // till now the saving needs to be done
        // fetching of the date via method is  done
        // also check the method update_inventory
        //for the auto reordering part need to be done
        // quantity verification at time of order need to be done
        // controller part
        return true;
    }
}



