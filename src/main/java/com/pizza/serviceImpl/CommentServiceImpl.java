package com.pizza.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.pizza.models.Comment;
import com.pizza.repositories.CommentRepo;
import com.pizza.services.customer.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public void saveComment(int orderId, Comment comment, String phoneNum) {
        comment.setOrderId(orderId);
        comment.setPhoneNum(phoneNum);
        commentRepo.save(comment);
    }

    @Override
    public List<Comment> findAllCommentsByPhoneNum(String phoneNum) {
        return commentRepo.findAllByPhoneNum(phoneNum);
    }

    @Override
    public void deleteCommentById(int id) {
        commentRepo.deleteById(id);
    }

    @Override
    public String findCreationTimeByOrderId(int orderId) {
        return commentRepo.findCreationTimeByOrderId(orderId);
    }

}

