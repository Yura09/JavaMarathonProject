package com.softserve.javamarathon.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"sprints", "users"})
@ToString(exclude = {"sprints", "users"})
@Table(name = "marathons")
public class Marathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3, max = 30, message = "title must be from 3 to 30  letters")
    private String title;
    @OneToMany(mappedBy = "marathon")
    private List<Sprint> sprints;
    @ManyToMany
    @JoinTable(name = "marathons_users",
            joinColumns = @JoinColumn(name = "marathon_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
