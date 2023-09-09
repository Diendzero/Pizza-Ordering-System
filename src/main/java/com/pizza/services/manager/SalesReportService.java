package com.pizza.services.manager;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pizza.models.OrderItems;
import com.pizza.models.Pizza;
import com.pizza.models.SalesReport;
import com.pizza.repositories.OrderItemsRepo;
import com.pizza.repositories.PizzaRepo;


@Service
public class SalesReportService {

    @Autowired
    PizzaRepo pizzaRepo;
    @Autowired
    OrderItemsRepo orderItemsRepo;

    public List<SalesReport> getAllSalesReport() {
        List<Pizza> pizzaList = pizzaRepo.findAll();
        List<OrderItems> orderItems = orderItemsRepo.findAll();

        int totalNum;
        if (pizzaList == null) throw new NullPointerException();
        List<SalesReport> salesReport = new ArrayList<>();
        for (int i = 0; i < pizzaList.size(); i++) {
            Pizza pizza = pizzaList.get(i);

            totalNum = 0;
            for (OrderItems item : orderItems) {
                if (item.getPizzaId() == pizza.getId()) {
                    totalNum += item.getNum();
                }
            }

            salesReport.add(i, new SalesReport(pizza.getId(),
                    pizza.getName(),
                    pizza.getSize(),
                    pizza.getBase(),
                    totalNum,
                    totalNum * Integer.parseInt(pizza.getPrice())));
        }
        return salesReport;
    }

    public List<SalesReport> getSalesReportByPizzaIds(List<Integer> pizzaIds) {
        List<Pizza> pizzaList = pizzaRepo.findAllById(pizzaIds);
        List<OrderItems> orderItems = orderItemsRepo.findAll();

        int totalNum;
        if (pizzaList == null) throw new NullPointerException();
        List<SalesReport> salesReport = new ArrayList<>();
        for (int i = 0; i < pizzaList.size(); i++) {
            Pizza pizza = pizzaList.get(i);

            totalNum = 0;
            for (OrderItems item : orderItems) {
                if (item.getPizzaId() == pizza.getId()) {
                    totalNum += item.getNum();
                }
            }

            salesReport.add(i, new SalesReport(pizza.getId(),
                    pizza.getName(),
                    pizza.getSize(),
                    pizza.getBase(), totalNum, totalNum * Integer.parseInt(pizza.getPrice())));
        }
        return salesReport;
    }

    public int getTotalTotalRevenue(List<SalesReport> reportList) {
        int totalTotalRevenue = 0;
        for (SalesReport salesReport : reportList) totalTotalRevenue += salesReport.getTotalRevenue();
        return totalTotalRevenue;
    }

}
