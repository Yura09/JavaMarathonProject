package com.softserve.javamarathon.entity;

import com.softserve.javamarathon.entity.enums.ROLE;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"marathons", "progresses"})
@ToString(exclude = {"marathons", "progresses"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    private String lastName;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ROLE role;
    @ManyToMany(mappedBy = "users")
    private List<Marathon> marathons;
    @OneToMany(mappedBy = "trainee")
    private List<Progress> progresses;
}
