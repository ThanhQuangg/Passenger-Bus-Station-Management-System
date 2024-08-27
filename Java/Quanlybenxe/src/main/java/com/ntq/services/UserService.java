package com.ntq.services;

import com.ntq.pojo.User;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User getUserByUsername(String username);

    void addUser(User user);
    
    User getUserById(int userID);

    boolean authUser(String username, String password);
    
    List<User> getUsers(Map<String, String> params);

    void addOrUpdate(User u);

    void deleteUser (int userID);
}
