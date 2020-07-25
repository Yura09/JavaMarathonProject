package com.softserve.javamarathon.repository;

import com.softserve.javamarathon.entity.Marathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarathonRepository extends JpaRepository<Marathon,Long> {

}
