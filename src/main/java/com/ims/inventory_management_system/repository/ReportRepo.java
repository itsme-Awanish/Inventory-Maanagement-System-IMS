package com.ims.inventory_management_system.repository;

import com.ims.inventory_management_system.model.Cart;
import com.ims.inventory_management_system.model.Order;
import com.ims.inventory_management_system.model.RecentOrdHist;
import com.ims.inventory_management_system.model.SalesReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportRepo {
    private JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Cart> findProduct(String oid){
        String sql = "select * from cart where oid = ?";
        return jdbc.query(sql,new Object[] {oid},(rs,rownum)->{
            Cart c = new Cart();
            c.setProname(rs.getString("proname"));
            c.setQuantity(rs.getInt("quantity"));
            c.setPrice(rs.getInt("price"));
            c.setCategory(rs.getString("category"));
            return c;
        });
    }
    public List<RecentOrdHist> orderHistory(){
        String sql = "SELECT c.cname AS 'Customer Name', o.oid AS 'Order ID', o.dop AS 'Date of Purchase', SUM(cart.price * cart.quantity) AS 'Total Amount' " +
                "FROM `orders` o " +
                "JOIN `cart` ON o.oid = cart.oid " +
                "JOIN `customer` c ON o.cid = c.cid " +
                "GROUP BY o.oid, c.cname, c.cid, o.dop " +
                "ORDER BY o.dop DESC " +
                "LIMIT 10";
        return jdbc.query(sql,(rs,rownum)->{
           RecentOrdHist roh = new RecentOrdHist();
            roh.setCustName(rs.getString("Customer Name"));
            roh.setOrder(rs.getString("Order ID"));
            roh.setCrt(findProduct(rs.getString("Order ID")));
            roh.setDop(rs.getDate("Date of Purchase"));
            roh.setTotalAmount(rs.getInt("Total Amount"));
            return roh;
        });
    }
    public List<Order> findOrder(){
        String sql = "select * from orders";
        return jdbc.query(sql,(rs,rownum)->{
            Order o = new Order();
            o.setOid(rs.getString("oid"));
            o.setDop(rs.getDate("dop"));
            o.setCid(rs.getString("cid"));
            return o;
        });
    }public List<SalesReport> findSalesReport(){
        String sql = "SELECT o.dop AS 'Date of Purchase', SUM(c.price * c.quantity) AS 'Total Price'\n" +
                "FROM `orders` o\n" +
                "JOIN `cart` c ON o.oid = c.oid\n" +
                "GROUP BY o.dop;";
        return jdbc.query(sql,(rs,rownum)->{
            SalesReport sr = new SalesReport();
            sr.setDop(rs.getDate("Date of Purchase"));
            sr.setPrice(rs.getInt("Total Price"));
            return sr;
        });
    }

    public List<Order> findCustomerOrder(String cid){
        String sql = "select * from orders where cid=?";
        return jdbc.query(sql,new Object[] {cid},(rs,rownum)->{
            Order o = new Order();
            o.setOid(rs.getString("oid"));
            o.setDop(rs.getDate("dop"));
            o.setCid(rs.getString("cid"));
            return o;
        });
    }

    public Long getLastMonthSales() {
        String sql = "SELECT SUM(c.price * c.quantity) AS 'Last Month Sales' " +
                "FROM `orders` o " +
                "JOIN `cart` c ON o.oid = c.oid " +
                "WHERE MONTH(o.dop) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) " +
                "AND YEAR(o.dop) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH)";

        return jdbc.queryForObject(sql, Long.class);
    }
    public Long getThisMonthSales() {
        String sql = "SELECT SUM(c.price * c.quantity) AS 'This Month Sales' " +
                "FROM `orders` o " +
                "JOIN `cart` c ON o.oid = c.oid " +
                "WHERE MONTH(o.dop) = MONTH(CURRENT_DATE) " +
                "AND YEAR(o.dop) = YEAR(CURRENT_DATE)";

        return jdbc.queryForObject(sql, Long.class);
    }
}
