package com.softserve.javamarathon.service.impl;

import com.softserve.javamarathon.entity.Progress;
import com.softserve.javamarathon.entity.Task;
import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.TaskStatus;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.ProgressRepository;
import com.softserve.javamarathon.repository.TaskRepository;
import com.softserve.javamarathon.repository.UserRepository;
import com.softserve.javamarathon.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressServiceImpl implements ProgressService {
    private ProgressRepository progressRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Autowired
    public ProgressServiceImpl(ProgressRepository progressRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.progressRepository = progressRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Progress getProgressById(Long id) {
        return progressRepository.findById(id).orElseThrow(() -> new NoEntityException(id + " not found"));
    }

    //???
    @Override
    @Transactional
    public Progress addTaskForStudent(Task task, User student) {
        Task taskEntity = taskRepository.getOne(task.getId());
        User userEntity = userRepository.getOne(student.getId());
        Progress progress = new Progress();
        progress.setTrainee(userEntity);
        progress.setTask(taskEntity);
        return progressRepository.save(progress);
    }

    @Override
    @Transactional
    public Progress addOrUpdateProgress(Progress progress) {

        if (progress.getId() != null) {
            Optional<Progress> entity = progressRepository.findById(progress.getId());
            if (entity.isPresent()) {
                Progress newProgress = entity.get();
                newProgress.setStarted(progress.getStarted());
                newProgress.setUpdated(progress.getUpdated());
                newProgress.setStatus(progress.getStatus());
                newProgress.setTask(progress.getTask());
                newProgress.setTrainee(progress.getTrainee());
                return progressRepository.save(newProgress);
            }
        }
        return progressRepository.save(progress);
    }

    @Override
    @Transactional
    public boolean setStatus(TaskStatus status, Progress progress) {
        Optional<Progress> progressEntityOpt = progressRepository.findById(progress.getId());
        if (progressEntityOpt.isPresent()) {
            Progress progressEntity = progressEntityOpt.get();
            if (progressEntity.getStatus() != status) {
                progressEntity.setStatus(status);
                return true;
            }
        }
        return false;

    }

    @Override
    @Transactional
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId) {
        List<Progress> progresses = progressRepository.findAllByTraineeIdAndMarathonId(userId, marathonId);
        if (progresses.isEmpty()) {
            throw new NoEntityException("nothing to show");
        }
        return progresses;
    }

    @Override
    @Transactional
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId) {
        List<Progress> progresses = progressRepository.findAllByTraineeIdAndSprintId(userId, sprintId);
        if (progresses.isEmpty()) {
            throw new NoEntityException("nothing to show");
        }
        return progresses;
    }
}
