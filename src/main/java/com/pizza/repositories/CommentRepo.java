package com.pizza.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.group20.pizza.models.Comment;


@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPhoneNum(String phoneNum);

    void deleteById(int id);

    @Query("SELECT o.creationTime FROM Orders o WHERE o.id = :orderId")
    String findCreationTimeByOrderId(@Param("orderId") int orderId);
}