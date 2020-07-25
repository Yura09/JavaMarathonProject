package com.softserve.javamarathon.service.impl;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.MarathonRepository;
import com.softserve.javamarathon.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class MarathonServiceImpl implements MarathonService {
    private MarathonRepository marathonRepository;

    @Autowired
    public void setMarathonRepository(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Override
    @Transactional
    public List<Marathon> getAll() {
        List<Marathon> marathons = marathonRepository.findAll();
        if (marathons.isEmpty()) {
            throw new NoSuchElementException("nothing to show");
        }
        return marathons;
    }

    @Override
    @Transactional
    public Marathon getMarathonById(Long id) {
        return marathonRepository.findById(id).orElseThrow(() -> new NoEntityException(id + " not found"));
    }

    @Override
    @Transactional
    public Marathon createOrUpdate(Marathon marathon) {
        if (marathon.getId() != null) {
            Optional<Marathon> entity = marathonRepository.findById(marathon.getId());
            if (entity.isPresent()) {
                Marathon newMarathon = entity.get();
                newMarathon.setTitle(marathon.getTitle());
                return marathonRepository.save(newMarathon);
            }
        }
        return marathonRepository.save(marathon);
    }

    @Override
    @Transactional
    public void deleteMarathonById(Long id) {
        Marathon marathon = marathonRepository.findById(id).orElseThrow(() -> new NoEntityException(id + " not found"));
        marathonRepository.delete(marathon);
    }
}
