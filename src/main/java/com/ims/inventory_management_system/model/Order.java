package com.ims.inventory_management_system.model;

import org.springframework.stereotype.Component;

@Component
public class Order {
    private String oid;
    private String dop;

    private String cid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDop() {
        return dop;
    }

    public void setDop(String dop) {
        this.dop = dop;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
