package com.ims.inventory_management_system.repository;

import com.ims.inventory_management_system.model.Cart;
import com.ims.inventory_management_system.model.Category;
import com.ims.inventory_management_system.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ims.inventory_management_system.model.Order;
import java.util.List;

@Repository
public class OrderRepo {

    JdbcTemplate Jdbc;

    public JdbcTemplate getJdbc() {
        return Jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        Jdbc = jdbc;
    }
    public void saveOrder(Order o){
        String sql = "insert into orders(oid,dop,cid) values (?,?,?)";
        Jdbc.update(sql,o.getOid(),o.getDop(),o.getCid());
    }

    public void saveProduct(String oid, List<Cart> c){

        String sql = "insert into cart(oid,proname,quantity,price,category) values(?,?,?,?,?)";
        for(Cart cart:c){
            Jdbc.update(sql, oid, cart.getProname(),cart.getQuantity(), cart.getPrice(), cart.getCategory());
        }
    }

}
