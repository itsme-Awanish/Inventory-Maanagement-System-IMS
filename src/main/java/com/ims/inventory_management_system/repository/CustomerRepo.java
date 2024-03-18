package com.ims.inventory_management_system.repository;

import com.ims.inventory_management_system.model.Customer;
import com.ims.inventory_management_system.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CustomerRepo {
    JdbcTemplate Jdbc;

    public JdbcTemplate getJdbc() {
        return Jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc){
        Jdbc = jdbc;
    }
    public boolean save(Customer c){
        boolean saved = false;
        String sql = "insert into customer(cid,cname,phone_no) values(?,?,?)";
        try {
            Jdbc.update(sql, c.getCid(), c.getCname(), c.getPhone_no());
            saved=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return saved;
    }

    public String getMaxCid() {
        String sql = "Select MAX(cid) AS max_value from customer";
        return Jdbc.queryForObject(sql,String.class);
    }
    public Customer getCustomer(Long pno){
        String sql = "select * from customer where phone_no = ?";
        try {
            return Jdbc.queryForObject(sql, new Object[]{pno}, new RowMapper<Customer>() {
                @Override
                public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Customer c = new Customer();
                    c.setCid(rs.getString("cid"));
                    c.setCname(rs.getString("cname"));
                    c.setPhone_no(rs.getLong("phone_no"));
                    return c;
                }
            });
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

}
