package com.pizza.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.google.common.base.Optional;
import com.pizza.models.OrderItems;


public interface OrderItemsRepo extends JpaRepository<OrderItems, Integer> {

    List<OrderItems> findByPhoneNumberAndIsInShoppingCartOrderById(String phoneNumber, int isInShoppingCart);

    List<OrderItems> findByOrderIdOrderById(int orderId);

    java.util.Optional<OrderItems> findByPhoneNumberAndPizzaIdAndIsInShoppingCart(String phoneNumber, int pizzaId, int isInShoppingCart);

    List<OrderItems> findByOrderId(int orderId);

    public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
        List<OrderItems> findByOrderId(Long orderId);
    }

}