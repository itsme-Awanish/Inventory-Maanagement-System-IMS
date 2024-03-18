package com.ims.inventory_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ims.inventory_management_system.repository.ProductRepo;
import com.ims.inventory_management_system.model.Product;
import java.util.List;

@Service
public class ProductService {

    private ProductRepo repo;

    public ProductRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(ProductRepo repo) {
        this.repo = repo;
    }

    public List<Product> getdata() {
        return repo.findAll();
    }
    public List<Product> getDataByCategory(String category) {
        return repo.findByCategory(category);
    }

    public int getMaxProId() {
        return (repo.getMaxProId()+1);
    }

    public void sendData(Product pro) {
        repo.save(pro);
    }

    public Product getProductById(int proid){
        return repo.getProById(proid);
    }
    public void updateProduct(Product pro) {
        repo.update(pro);
    }

    public void deleteProduct(int id) {
        repo.delete(id);
    }
}