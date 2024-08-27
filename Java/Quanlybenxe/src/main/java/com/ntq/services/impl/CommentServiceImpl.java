package com.ntq.services.impl;

import com.ntq.pojo.Comment;
import com.ntq.repositories.CommentRepository;
import com.ntq.services.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getComment(Map<String, String> params) {
        return this.commentRepository.getComments(params);
    }

    @Override
    public void addOrUpdate(Comment c) {
        this.commentRepository.addOrUpdate(c);
    }

    @Override
    public Comment getCommentById(int commentID) {
        return this.commentRepository.getCommentByID(commentID);
    }

    @Override
    public void deleteComment(int commentID) {
        this.commentRepository.deleteComment(commentID);
    }

}
