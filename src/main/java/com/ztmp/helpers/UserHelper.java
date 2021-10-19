package com.ztmp.helpers;

import com.ztmp.enums.Role;
import com.ztmp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserHelper {

    private HashMap<String, User> users;

    public UserHelper() {
        users = new HashMap<>();
        users.put("user", new com.ztmp.model.User(1, "user", "user", Role.USER));
        users.put("user1", new com.ztmp.model.User(2, "user1", "user1", Role.USER));
        users.put("user2", new com.ztmp.model.User(3, "user2", "user2", Role.USER));
        users.put("user3", new com.ztmp.model.User(4, "user3", "user3", Role.USER));
        users.put("admin", new com.ztmp.model.User(5, "admin", "admin", Role.ADMIN));
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public User getUser(String login) {
        for (Map.Entry<String, com.ztmp.model.User> userU : this.users.entrySet()) {
            if (userU.getKey().equals(login))
                return userU.getValue();
        }
        return null;
    }

}
