package com.softserve.javamarathon.service.impl;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.ROLE;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.MarathonRepository;
import com.softserve.javamarathon.repository.UserRepository;
import com.softserve.javamarathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private MarathonRepository marathonRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MarathonRepository marathonRepository) {
        this.userRepository = userRepository;
        this.marathonRepository = marathonRepository;

    }

    @Override
    @Transactional
    public List<User> getAll() {
        List<User> users = userRepository.findAll();

        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoEntityException(id + " not found"));
    }

    @Override
    @Transactional
    public User createOrUpdateUser(User user) {
        if (user.getId() != null) {
            Optional<User> entity = userRepository.findById(user.getId());
            if (entity.isPresent()) {
                User newUser = entity.get();
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setEmail(user.getEmail());
                newUser.setPassword(user.getPassword());
                newUser.setRole(user.getRole());
                return userRepository.save(newUser);
            }
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getAllByRole(String role) {

        List<User> users = userRepository.findAllByRole(ROLE.valueOf(role));
        return users.isEmpty() ? new ArrayList<>() : users;
    }

    @Override
    @Transactional
    public boolean addUserToMarathon(User user, Marathon marathon) {
        User userEntity = userRepository.getOne(user.getId());
        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
        marathonEntity.getUsers().add(userEntity);
        return marathonRepository.save(marathonEntity) != null;
    }


}

