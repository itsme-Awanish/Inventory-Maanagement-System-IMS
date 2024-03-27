package com.ims.inventory_management_system.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ims.inventory_management_system.model.Category;
import java.util.List;

@Repository
public class CategoryRepo {

    public JdbcTemplate jdbc;

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    public void save(Category cat) {
        String sql = "insert into category(catId,catName) value (?,?)";
        int rows = jdbc.update(sql,cat.getCatId(),cat.getCatName());
        System.out.println(rows + "affected");
    }

    public int getMaxCatId() {
        String sql = "SELECT MAX(catId) AS max_value FROM category";
        return jdbc.queryForObject(sql, Integer.class);
    }

    public void update(Category cat){
        String sql = "update category set catname=? where catid=?";
        int rows = jdbc.update(sql,cat.getCatName(),cat.getCatId());
        System.out.println(rows + "affected");
    }
    public void delete(int id){
        String sql = "delete from category where catid=?";
        int rows = jdbc.update(sql,id);
        System.out.println(rows + "affected");
    }
    public List<Category> findAll() {
        String sql = "select * from category order by catid asc";
        return jdbc.query(sql,(rs, rowNum)->{
            Category cat = new Category();
            cat.setCatId(rs.getInt("catId"));
            cat.setCatName(rs.getString("catName"));
            return cat;
        });
    }
}
