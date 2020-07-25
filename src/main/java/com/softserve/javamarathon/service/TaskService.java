package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.Sprint;
import com.softserve.javamarathon.entity.Task;

public interface TaskService {
    Task addTaskToSprint(Task task, Sprint sprint);

    Task getTaskById(Long id);
}
