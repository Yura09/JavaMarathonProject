package com.softserve.javamarathon.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"progresses"})
@ToString(exclude = {"progresses"})
@Builder
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 30, message = "title must be from 3 to 30 letters")
    private String title;
    @NotNull
    @CreationTimestamp
    private LocalDate created;
    @NotNull
    @UpdateTimestamp
    private LocalDate updated;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
    @OneToMany(mappedBy = "task")
    private List<Progress> progresses;

}
