package com.softserve.javamarathon.service.impl;

import com.softserve.javamarathon.entity.Sprint;
import com.softserve.javamarathon.entity.Task;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.SprintRepository;
import com.softserve.javamarathon.repository.TaskRepository;
import com.softserve.javamarathon.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private SprintRepository sprintRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    public void setSprintRepository(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    @Transactional
    public Task addTaskToSprint(Task task, Sprint sprint) {
        return sprintRepository.findById(sprint.getId()).map(obj -> {
            task.setSprint(obj);
            return taskRepository.save(task);
        }).orElseThrow(() -> new NoEntityException("sprint " + sprint.getId() + " not found"));
    }

    @Override
    @Transactional
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoEntityException(id + " not found"));

    }
}
