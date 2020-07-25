package com.softserve.javamarathon.repository;

import com.softserve.javamarathon.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long> {
    //@Query("select s from Sprint s where s.marathon.id= ?1")
    List<Sprint> findByMarathonId(Long marathonId);
}
