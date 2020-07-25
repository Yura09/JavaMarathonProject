package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.entity.Sprint;

import java.util.List;

public interface SprintService {
    List<Sprint> getSprintsByMarathonId(Long marathonId);
    boolean addSprintToMarathon(Sprint sprint, Marathon marathon);
    boolean updateSprint(Sprint sprint);
    Sprint getSprintById(Long id);
}
