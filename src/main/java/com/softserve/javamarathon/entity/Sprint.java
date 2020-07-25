package com.softserve.javamarathon.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"tasks"})
@ToString(exclude = {"tasks"})
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
    @Size(min = 3, max = 30, message = "title must be from 3 to 30 letters")
    private String title;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "marathon_id")
    private Marathon marathon;
    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;
}
