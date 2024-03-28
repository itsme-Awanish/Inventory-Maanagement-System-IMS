package com.ims.inventory_management_system.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryReport {
    private List<StockReport> stkReport;
    private List<SalesReport> slsReport;
    private List<RecentOrdHist> roh;

    private int inventorySize;
    private Long lastMonthTurnover;
    private Long thisMonthTurnover;

    public List<StockReport> getStkReport() {
        return stkReport;
    }

    public void setStkReport(List<StockReport> stkReport) {
        this.stkReport = stkReport;
    }

    public List<SalesReport> getSlsReport() {
        return slsReport;
    }

    public void setSlsReport(List<SalesReport> slsReport) {
        this.slsReport = slsReport;
    }

    public List<RecentOrdHist> getRoh() {
        return roh;
    }

    public void setRoh(List<RecentOrdHist> roh) {
        this.roh = roh;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public Long getLastMonthTurnover() {
        return lastMonthTurnover;
    }

    public void setLastMonthTurnover(Long lastMonthTurnover) {
        this.lastMonthTurnover = lastMonthTurnover;
    }

    public Long getThisMonthTurnover() {
        return thisMonthTurnover;
    }

    public void setThisMonthTurnover(Long thisMonthTurnover) {
        this.thisMonthTurnover = thisMonthTurnover;
    }
}
