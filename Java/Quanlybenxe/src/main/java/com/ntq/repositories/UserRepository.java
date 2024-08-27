package com.ntq.repositories;

import com.ntq.pojo.User;
import java.util.List;
import java.util.Map;

public interface UserRepository {

    User getUserByUsername(String username);

    void addUser(User user);
    
    User getUserById(int userID);

    boolean authUser(String username, String password);
    
    List<User> getUsers(Map<String, String> params);

    void addOrUpdate(User u);

    void deleteUser (int userID);
}
