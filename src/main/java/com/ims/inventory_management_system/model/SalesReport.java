package com.ims.inventory_management_system.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SalesReport {
    private Date Dop;
    private int price;

    public Date getDop() {
        return Dop;
    }

    public void setDop(Date dop) {
        Dop = dop;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
