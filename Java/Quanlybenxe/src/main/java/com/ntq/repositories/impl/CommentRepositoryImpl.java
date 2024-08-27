package com.ntq.repositories.impl;

import com.ntq.pojo.Comment;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ntq.repositories.CommentRepository;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factoryBean;

    @Override
    public List<Comment> getComments(Map<String, String> params) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Comment.findAll");

        return q.getResultList();
    }

    @Override
    public void addOrUpdate(Comment c) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        s.saveOrUpdate(c);
    }

    @Override
    public Comment getCommentByID(int commentID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        return s.get(Comment.class, commentID);
    }

    @Override
    public void deleteComment(int commentID) {
        Session s = this.factoryBean.getObject().getCurrentSession();
        Comment c = this.getCommentByID(commentID);
        s.delete(c);
    }

}
