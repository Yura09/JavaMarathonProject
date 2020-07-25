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
            " join fetch p.trainee tr"+
            " join fetch p.task t" +
            " join fetch t.sprint s" +
            " join fetch s.marathon m where m.id= :marathonId and tr.id= :traineeId",nativeQuery = true)
    List<Progress> findAllByTraineeIdAndMarathonId(@Param("traineeId") Long userId, @Param("marathonId") Long marathonId);

    @Query(value = "select p from Progress p" +
            " join fetch p.task t" +
            " join fetch t.sprint s where s.id= :sprintId and p.trainee.id= :traineeId",nativeQuery = true)
    List<Progress> findAllByTraineeIdAndSprintId(@Param("traineeId") Long userId, @Param("sprintId") Long SprintId);
}
