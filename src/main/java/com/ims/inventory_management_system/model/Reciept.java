package com.ims.inventory_management_system.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Reciept {
    private String oid;
    private Date dop;
    private String cname = null;
    private Long phone_no;
    private List<Cart> cart = new ArrayList<Cart>();
    private int totalAmount;
    private boolean isFilled = false;
    private boolean isNOFilled = false;
    private boolean custFoundDb = false;
    private String message;


    public boolean isCustFoundDb() {
        return custFoundDb;
    }

    public void setCustFoundDb(boolean custFoundDb) {
        this.custFoundDb = custFoundDb;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getDop() {
        return dop;
    }
    public void setDop(Date dop) {
        this.dop = dop;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Long getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(Long phone_no) {
        this.phone_no = phone_no;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public boolean isNOFilled() {
        return isNOFilled;
    }

    public void setNOFilled(boolean NOFilled) {
        isNOFilled = NOFilled;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Reciept{" +
                "oid='" + oid + '\'' +
                ", dop='" + dop + '\'' +
                ", cname='" + cname + '\'' +
                ", phone_no=" + phone_no +
                ", products=" + cart +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
