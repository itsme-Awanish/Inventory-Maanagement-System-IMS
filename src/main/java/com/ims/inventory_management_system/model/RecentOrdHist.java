package com.ims.inventory_management_system.model;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RecentOrdHist {
    private String custName;
    private String order;
    private List <Cart> crt;

    private Date dop;
    private int totalAmount;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Cart> getCrt() {
        return crt;
    }

    public void setCrt(List<Cart> crt) {
        this.crt = crt;
    }

    public Date getDop() {
        return dop;
    }

    public void setDop(Date dop) {
        this.dop = dop;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
