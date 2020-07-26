package com.softserve.javamarathon.repository;

import com.softserve.javamarathon.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    @Query(value = "select p from Progress p" +
            " join  p.trainee tr"+
            " join  p.task t" +
            " join  t.sprint s" +
            " join  s.marathon m where m.id = ?2 and tr.id = ?1")
    List<Progress> findAllByTraineeIdAndMarathonId(Long userId,Long marathonId);

    @Query(value = "select p from Progress p" +
            " join  p.task t" +
            " join  t.sprint s where s.id = ?2 and p.trainee.id = " +
            "?1")
    List<Progress> findAllByTraineeIdAndSprintId(@Param("traineeId") Long userId, @Param("sprintId") Long SprintId);
}
