package com.ims.inventory_management_system.Controller;


import com.ims.inventory_management_system.model.InventoryReport;
import com.ims.inventory_management_system.model.StockReport;
import com.ims.inventory_management_system.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {

    ReportService rService;

    @Autowired
    public void setrService(ReportService rService) {
        this.rService = rService;
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(@ModelAttribute InventoryReport ivr){
        ModelAndView mv= new ModelAndView();
        ivr = rService.getReport();
        for(StockReport s : ivr.getStkReport()){
            System.out.print(s.getCategoryName()+"    ");
            System.out.println(s.getQuantity());
        }
        mv.addObject("stockReport",ivr.getStkReport());
        mv.addObject("salesReport",ivr.getSlsReport());
        mv.addObject("inventorySize",ivr.getInventorySize());
        mv.addObject("lastMonthSales",ivr.getLastMonthTurnover());
        mv.addObject("thisMonthSales",ivr.getThisMonthTurnover());
        mv.addObject("recentOrderHistory", ivr.getRoh());
        mv.setViewName("dashboard");
        return mv;
    }
}
