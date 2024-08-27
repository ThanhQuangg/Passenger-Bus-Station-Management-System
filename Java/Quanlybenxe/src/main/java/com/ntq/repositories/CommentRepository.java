package com.ntq.repositories;

import com.ntq.pojo.Comment;
import java.util.List;
import java.util.Map;

public interface CommentRepository {

    List<Comment> getComments(Map<String, String> params);

    void addOrUpdate(Comment c);

    Comment getCommentByID(int commentID);

    void deleteComment(int commentID);
}
