package com.ims.inventory_management_system.repository;

import com.ims.inventory_management_system.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {
    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate jdbc;
    public void save(Users u){
        String sql = "insert into users(uid,name,uname,upass) value (?,?,?,?)";
        int rows = jdbc.update(sql,u.getUid(),u.getName(),u.getUname(),u.getUpass());
        System.out.println(rows + "effected");
    }
    public List<Users> findAll(){
        String sql = "select * from users";
        return jdbc.query(sql,(rs, rowNum)->{
            Users u = new Users();
            u.setUid(rs.getInt("uid"));
            u.setName(rs.getString("name"));
            u.setUname(rs.getString("uname"));
            u.setUpass(rs.getString("upass"));
            return u;
        });
    }
    public int getMaxUid() {
        String sql = "SELECT MAX(uid) AS max_value FROM users";
        return jdbc.queryForObject(sql, Integer.class);
    }

    public void updateData(Users u) {
        String sql = "update users set name = ?, uname = ?, upass = ? where uid = ?";
        int rows = jdbc.update(sql,u.getName(),u.getUname(),u.getUpass(),u.getUid());
        System.out.println(rows + "effected");
    }

    public void deleteData(int uid) {
        String sql = "delete from users where uid = ?";
        int rows = jdbc.update(sql,uid);
        System.out.println(rows + "effected");
    }
}