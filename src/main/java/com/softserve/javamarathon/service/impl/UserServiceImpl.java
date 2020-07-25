package com.softserve.javamarathon.service.impl;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.entity.Progress;
import com.softserve.javamarathon.entity.Task;
import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.ROLE;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.MarathonRepository;
import com.softserve.javamarathon.repository.ProgressRepository;
import com.softserve.javamarathon.repository.TaskRepository;
import com.softserve.javamarathon.repository.UserRepository;
import com.softserve.javamarathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private MarathonRepository marathonRepository;
    private TaskRepository taskRepository;
    private ProgressRepository progressRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMarathonRepository(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoEntityException("nothing to show");
        }
        return users;
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
        if (users.isEmpty()) {
            throw new NoEntityException("nothing to show");
        }
        return users;
    }

    @Override
    @Transactional
    public boolean addUserToMarathon(User user, Marathon marathon) {
        return marathonRepository.findById(marathon.getId()).map(obj -> {
            user.getMarathons().add(obj);
            return userRepository.save(user) != null;
        }).orElse(false);
    }

    //???
    @Override
    @Transactional
    public boolean addUserToTask(User user, Task task) {
        User userEntity = userRepository.getOne(user.getId());
        Task taskEntity = taskRepository.getOne(task.getId());
        Progress progress = userEntity.getProgresses().stream().filter(x -> x.getTrainee().equals(userEntity)).findFirst().get();
        progress.setTask(taskEntity);
       return progressRepository.save(progress)!=null;
       /* return taskRepository.findById(task.getId()).map(obj -> {
            user.getProgresses().stream().filter(progress -> progress.getTask().equals(task)).findFirst().get().setTask(obj);
            return userRepository.save(user)!=null;

        }).orElse(false);*/
    }
}
