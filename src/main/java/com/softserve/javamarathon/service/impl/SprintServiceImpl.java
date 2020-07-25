package com.softserve.javamarathon.service.impl;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.entity.Sprint;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.MarathonRepository;
import com.softserve.javamarathon.repository.SprintRepository;
import com.softserve.javamarathon.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SprintServiceImpl implements SprintService {
    private SprintRepository sprintRepository;
    private MarathonRepository marathonRepository;

    @Autowired
    public void setSprintRepository(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Autowired
    public void setMarathonRepository(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Override
    @Transactional
    public List<Sprint> getSprintsByMarathonId(Long marathonId) {
        return sprintRepository.findByMarathonId(marathonId);
    }

    @Override
    @Transactional
    public boolean addSprintToMarathon(Sprint sprint, Marathon marathon) {
        return marathonRepository.findById(marathon.getId()).map(obj ->
        {
            sprint.setMarathon(obj);
            sprintRepository.save(sprint);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional
    public boolean updateSprint(Sprint sprint) {
        return sprintRepository.findById(sprint.getId()).map(obj -> {
            obj.setFinish(sprint.getFinish());
            obj.setStartDate(sprint.getStartDate());
            obj.setTitle(sprint.getTitle());
            obj.setMarathon(sprint.getMarathon());
            sprintRepository.save(obj);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional
    public Sprint getSprintById(Long id) {
        return sprintRepository.findById(id).orElseThrow(() -> new NoEntityException(id+ " not found"));
    }
}
