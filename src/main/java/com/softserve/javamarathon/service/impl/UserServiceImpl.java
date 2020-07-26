package com.softserve.javamarathon.service.impl;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.entity.Progress;
import com.softserve.javamarathon.entity.Task;
import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.ROLE;
import com.softserve.javamarathon.entity.enums.TaskStatus;
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
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private MarathonRepository marathonRepository;
    private TaskRepository taskRepository;
    private ProgressRepository progressRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MarathonRepository marathonRepository, TaskRepository taskRepository, ProgressRepository progressRepository) {
        this.userRepository = userRepository;
        this.marathonRepository = marathonRepository;
        this.taskRepository = taskRepository;
        this.progressRepository = progressRepository;
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
        User userEntity = userRepository.getOne(user.getId());
        Marathon marathonEntity = marathonRepository.getOne(marathon.getId());
        marathonEntity.getUsers().add(userEntity);
        return marathonRepository.save(marathonEntity) != null;
    }

    //???
    @Override
    @Transactional
    public boolean addUserToTask(User user, Task task) {
        User userEntity = userRepository.getOne(user.getId());
        Task taskEntity = taskRepository.getOne(task.getId());

        Progress newProgress = Progress.builder().updated(taskEntity.getUpdated()).task(taskEntity).started(taskEntity.getCreated()).trainee(userEntity).status(TaskStatus.PENDING).build();
        taskEntity.getProgresses().add(newProgress);
        progressRepository.save(newProgress);

        // taskRepository.save(task);
        //   Progress newProgress = taskEntity.getProgresses().stream().filter(progress -> progress.getTask().equals(taskEntity)).findFirst().get();
       /* Progress p = new Progress();
        p.setTrainee(userEntity);
        p.setTask(taskEntity);*/
//        newProgress.setTrainee(userEntity);
        return taskRepository.save(taskEntity) != null;

        // taskEntity.getProgresses().stream()
       /* return taskRepository.findById(task.getId()).map(obj -> {
            user.getProgresses().stream().filter(progress -> progress.getTask().equals(task)).findFirst().get().setTask(obj);
            return userRepository.save(user)!=null;

        }).orElse(false);*/
    }
}
