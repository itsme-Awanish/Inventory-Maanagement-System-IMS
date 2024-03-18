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
        for(int i=0;i<=c.size();i++){
            Cart cart = c.get(i);
            Jdbc.update(sql, oid, cart.getProname(),cart.getQuantity(), cart.getPrice(), cart.getCategory());
        }
    }
    public List<Cart> findProduct(String oid){
        String sql = "select * from cart where oid = ?";
        return Jdbc.query(sql,new Object[] {oid},(rs,rownum)->{
            Cart c = new Cart();
            c.setProname(rs.getString("proname"));
            c.setQuantity(rs.getInt("quantity"));
            c.setPrice(rs.getInt("price"));
            c.setCategory(rs.getString("category"));
            return c;
        });
    }
    public List<Order> findOrder(){
        String sql = "select * from orders";
        return Jdbc.query(sql,(rs,rownum)->{
            Order o = new Order();
            o.setOid(rs.getString("oid"));
            o.setDop(rs.getString("dop"));
            o.setCid(rs.getString("cid"));
            return o;
        });
    }

    public List<Order> findCustomerOrder(String cid){
        String sql = "select * from orders where cid=?";
        return Jdbc.query(sql,new Object[] {cid},(rs,rownum)->{
           Order o = new Order();
           o.setOid(rs.getString("oid"));
           o.setDop(rs.getString("dop"));
           o.setCid(rs.getString("cid"));
           return o;
        });
    }
}
