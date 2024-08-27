package com.ntq.repositories.impl;

import com.ntq.pojo.User;
import com.ntq.repositories.UserRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);

        return (User) q.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        if (user != null) {
            Session s = this.factory.getObject().getCurrentSession();
            s.save(user);
        } else {
            throw new IllegalArgumentException("User object is null");
        }
    }

    @Override
    public User getUserById(int userID) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(User.class, userID);
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public List<User> getUsers(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        Query query = session.createQuery(q);
        List<User> users = query.getResultList();

        return users;
    }

    @Override
    public void addOrUpdate(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.saveOrUpdate(u);
    }

    @Override
    public void deleteUser(int userID) {
        Session s = this.factory.getObject().getCurrentSession();
        User u = this.getUserById(userID);
        s.delete(u);
    }

}
