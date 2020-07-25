package com.softserve.javamarathon.repository;

import com.softserve.javamarathon.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {
    List<Sprint> findByMarathonId(Long marathonId);
}
