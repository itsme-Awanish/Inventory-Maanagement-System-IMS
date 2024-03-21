package com.ims.inventory_management_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.ims.inventory_management_system.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepo {

    public JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Product pro){
        String sql = "insert into product(proId,proName,quantity,price,category) value (?,?,?,?,?)";
        int rows = jdbc.update(sql,pro.getProId(),pro.getProName(),pro.getQuantity(),pro.getPrice(),pro.getCategory());
        System.out.println(rows + "affected");
    }

    public int getMaxProId() {
        String sql = "SELECT MAX(proId) AS max_value FROM product";
        return jdbc.queryForObject(sql, Integer.class);
    }

    public void update(Product pro){
        String sql = "update product set proname=?, quantity=?, price=?, category=? where proid=?";
        int rows = jdbc.update(sql,pro.getProName(),pro.getQuantity(),pro.getPrice(),pro.getCategory(),pro.getProId());
        System.out.println(rows + "affected");
    }
    public void delete(int id){
        String sql = "delete from product where proid=?";
        int rows = jdbc.update(sql,id);
        System.out.println(rows + "affected");
    }
    public List<Product> findAll() {
        String sql = "select * from product";
        return jdbc.query(sql,(rs, rowNum)->{
            Product pro = new Product();
            pro.setProId(rs.getInt("proid"));
            pro.setProName(rs.getString("proname"));
            pro.setQuantity(rs.getInt("quantity"));
            pro.setPrice(rs.getInt("price"));
            pro.setCategory(rs.getString("category"));
            return pro;
        });
    }

    public List<Product> findByCategory(String category) {
        String sql = "select * from product where category = ?";
        return jdbc.query(sql, new Object[]{category},(rs,rownum)->{
           Product pro = new Product();
           pro.setProId(rs.getInt("proid"));
            pro.setProName(rs.getString("proname"));
            pro.setQuantity(rs.getInt("quantity"));
            pro.setPrice(rs.getInt("price"));
            pro.setCategory(rs.getString("category"));
            return pro;
        });
    }

    public Product getProById(int proid) {
        String sql = "Select * from product where proid = ?";
        return jdbc.queryForObject(sql, new Object[]{proid}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product p = new Product();
                p.setProId(rs.getInt("proid"));
                p.setProName(rs.getString("proname"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getInt("price"));
                p.setCategory(rs.getString("category"));
                return p;
            }
        });
    }
}


