package com.pizza.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pizza.models.Orders;


public interface OrdersRepo extends JpaRepository<Orders, Integer> {

    List<Orders> findByPaymentStatus(String paymentStatus);

    List<Orders> findOrdersByPhoneNum(String phoneNum);

    Optional<Orders> findById(int id);

    List<Orders> findOrdersByPhoneNumAndPaymentStatus(String phoneNumber, String paymentStatus);

}