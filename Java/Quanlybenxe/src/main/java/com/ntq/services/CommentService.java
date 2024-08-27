package com.ntq.services;

import com.ntq.pojo.Comment;
import java.util.List;
import java.util.Map;

public interface CommentService {
    List<Comment> getComment(Map<String, String> params);

    void addOrUpdate(Comment c);

    Comment getCommentById(int commentID);

    void deleteComment(int commentID);
}
