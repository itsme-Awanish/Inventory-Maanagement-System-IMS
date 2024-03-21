package com.ims.inventory_management_system.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Order {
    private String oid;
    private Date dop;

    private String cid;

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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
