package com.pizza.services.manager;

import com.pizza.models.Orders;
import java.util.List;


public interface OrdersService {

    void insertOrder(Orders order);

    void deleteOrder(Integer id);

    void deleteMultipleOrder(String ids);

    void updateOrder(Orders order);

    List<Orders> selectAllOrder();

    List<Orders> selectLike(String search);

}
