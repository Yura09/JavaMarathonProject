package com.softserve.javamarathon.entity;

import com.softserve.javamarathon.entity.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "progresses")
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDate started;

    @Enumerated(EnumType.STRING)
    private TaskStatus status=TaskStatus.NEW;

    @UpdateTimestamp
    private LocalDate updated;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "task_id")
    private Task task;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "trainee_id")
    private User trainee;
}
