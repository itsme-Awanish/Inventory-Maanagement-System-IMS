package com.ims.inventory_management_system.service;

import com.ims.inventory_management_system.model.*;
import com.ims.inventory_management_system.repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {
    private ProductService pService;
    private CategoryService cService;
    private ReportRepo reportRepo;
    private final int INVENTORYSIZE = 300;

    @Autowired
    public void setpService(ProductService pService) {
        this.pService = pService;
    }
    @Autowired
    public void setcService(CategoryService cService) {
        this.cService = cService;
    }

    @Autowired
    public void setReportRepo(ReportRepo reportRepo) {
        this.reportRepo = reportRepo;
    }




    private List<StockReport> stockReport(){
        int inventorySize = INVENTORYSIZE;
        List<StockReport> stReport = new ArrayList<>();
        List<Category> categories = cService.getdata();
        for(Category c: categories){
            List<Product> p = pService.getDataByCategory(c.getCatName());
            if (p.isEmpty()){
                continue;
            }else {
                int quantity = 0;
                for (Product pro : p) {
                    quantity += pro.getQuantity();
                }
                StockReport s = new StockReport();
                s.setCategoryName(c.getCatName());
                s.setQuantity(quantity);
                inventorySize -= quantity;
                stReport.add(s);
            }
        }
        StockReport s = new StockReport();
        s.setCategoryName("Space Left");
        s.setQuantity(inventorySize);
        stReport.add(s);
        return stReport;
    }

    private List<SalesReport> salesReports(){
        return reportRepo.findSalesReport();
    }

    public InventoryReport getReport(){
        InventoryReport ir = new InventoryReport();
        ir.setStkReport(stockReport());
        ir.setSlsReport(salesReports());
        ir.setInventorySize(INVENTORYSIZE);
        ir.setLastMonthTurnover(reportRepo.getLastMonthSales());
        ir.setThisMonthTurnover(reportRepo.getThisMonthSales());
        ir.setRoh(reportRepo.orderHistory());

        return ir;
    }
}
