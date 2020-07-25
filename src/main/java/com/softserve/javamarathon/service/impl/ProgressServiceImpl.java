package com.softserve.javamarathon.service.impl;

import com.softserve.javamarathon.entity.Progress;
import com.softserve.javamarathon.entity.Task;
import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.TaskStatus;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.ProgressRepository;
import com.softserve.javamarathon.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProgressServiceImpl implements ProgressService {
    private ProgressRepository progressRepository;

    @Autowired
    public void setProgressRepository(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    @Transactional
    public Progress getProgressById(Long id) {
        return progressRepository.findById(id).orElseThrow(() -> new NoEntityException(id + " not found"));
    }

    @Override
    @Transactional
    public Progress addTaskForStudent(Task task, User student) {
        return null;
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
        Progress entity = getProgressById(progress.getId());
        entity.setStatus(status);
        return progressRepository.save(entity) != null;

    }

    @Override
    @Transactional
    public List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId) {
    List<Progress>progresses=progressRepository.findAllByTraineeIdAndMarathonId(userId, marathonId);
        if (progresses.isEmpty()) {
            throw new NoEntityException("nothing to show");
        }
        return progresses;
    }

    @Override
    @Transactional
    public List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId) {
        List<Progress>progresses=progressRepository.findAllByTraineeIdAndSprintId(userId, sprintId);
        if (progresses.isEmpty()) {
            throw new NoEntityException("nothing to show");
        }
        return progresses;
    }
}
