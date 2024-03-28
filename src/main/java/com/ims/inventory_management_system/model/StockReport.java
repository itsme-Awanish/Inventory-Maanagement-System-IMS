package com.ims.inventory_management_system.model;

import org.springframework.stereotype.Component;

@Component
public class StockReport {
    private String CategoryName;
    private int quantity;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.CategoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
