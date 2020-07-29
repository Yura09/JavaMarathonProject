package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getUserById(Long id);

    User createOrUpdateUser(User user);

    List<User> getAllByRole(String role);

    boolean addUserToMarathon(User user, Marathon marathon);
    void deleteUserById(Long id);

}
