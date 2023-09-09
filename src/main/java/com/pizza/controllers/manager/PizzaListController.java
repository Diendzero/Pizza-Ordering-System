package com.pizza.controllers.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.pizza.models.SalesReport;
import com.pizza.services.manager.PizzaService;
import com.pizza.services.manager.SalesReportService;

@RestController
public class PizzaListController {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private SalesReportService salesReportService;

    @RequestMapping(value = "/pizzalist")
    public ModelAndView toPizzaList() {
        ModelAndView view = new ModelAndView("manager/pizzalist");
        view.addObject("pizza_list", pizzaService.selectAllPizza());
        try {
            salesReportService.getAllSalesReport();
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Exception! CHECK IT !!!!!");
            return view;
        }
        List<SalesReport> salesReports = salesReportService.getAllSalesReport();
        view.addObject("salesReport", salesReports);
        view.addObject("totalTotalRevenue", salesReportService.getTotalTotalRevenue(salesReports));
        return view;
    }

    @PostMapping(value = "/pizzalist")
    public ModelAndView getSalesReportByPizzaId(@RequestParam List<Integer> pizzaIds) {
        ModelAndView view = new ModelAndView("manager/pizzalist");
        List<SalesReport> salesReports = salesReportService.getSalesReportByPizzaIds(pizzaIds);
        view.addObject("pizza_list", pizzaService.selectAllPizza());
        view.addObject("totalTotalRevenue", salesReportService.getTotalTotalRevenue(salesReports));
        view.addObject("salesReport", salesReports);
        return view;
    }

}

