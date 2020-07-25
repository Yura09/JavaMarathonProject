package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.Marathon;

import java.util.List;

public interface MarathonService {
    List<Marathon> getAll();
    Marathon getMarathonById(Long id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(Long id);
}
