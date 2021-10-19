package com.ztmp.service;

import com.ztmp.enums.Role;
import com.ztmp.helpers.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    private static HashMap<String, com.ztmp.model.User> users;
    private final UserHelper repository;

    @Autowired
    public UserService(UserHelper repository) {
        this.repository = repository;
        users = this.repository.getUsers();
    }

    public static UserDetails getUser(String login) {
        for (Map.Entry<String, com.ztmp.model.User> userU : users.entrySet()) {
            if (userU.getKey().equals(login)){
                return getByRoles(userU.getValue());
            }
        }
        return null;
    }

    private static UserDetails mapToUserDetails(com.ztmp.model.User user) {
        return User.withUsername(user.getLogin()).password(user.getPass()).roles(user.getRole().toString()).build();
    }

    private static UserDetails mapToUserDetailsAdminRole(com.ztmp.model.User user) {
        return User.withUsername(user.getLogin()).password(user.getPass()).roles(user.getRole().toString(), "USER").build();
    }

    private static UserDetails getByRoles(com.ztmp.model.User user) {
        return Role.ADMIN == user.getRole() ? mapToUserDetailsAdminRole(user) : mapToUserDetails(user);
    }

}
