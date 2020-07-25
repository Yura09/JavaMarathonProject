package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.Progress;
import com.softserve.javamarathon.entity.Task;
import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.TaskStatus;

import java.util.List;

public interface ProgressService {
    Progress getProgressById(Long id);

    Progress addTaskForStudent(Task task, User student);

    Progress addOrUpdateProgress(Progress progress);

    boolean setStatus(TaskStatus status, Progress progress);

    List<Progress> allProgressByUserIdAndMarathonId(Long userId, Long marathonId);

    List<Progress> allProgressByUserIdAndSprintId(Long userId, Long sprintId);
}
