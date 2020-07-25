package com.softserve.javamarathon.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sprints")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate finish;
    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;
    @NotNull
    private String title;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "marathon_id")
    private Marathon marathon;
    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;
}
