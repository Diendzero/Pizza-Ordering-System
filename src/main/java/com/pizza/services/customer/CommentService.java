package com.group20.pizza.services.customer;

import com.group20.pizza.models.Comment;
import java.util.List;

public interface CommentService {
    void saveComment(int orderId, Comment comment, String phoneNum);
    List<Comment> findAllCommentsByPhoneNum(String phoneNum);
    void deleteCommentById(int id);   
    String findCreationTimeByOrderId(int orderId);
}
