package com.softserve.javamarathon.entity;

import com.softserve.javamarathon.entity.enums.ROLE;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Size(min = 3, max = 30, message = "First name must be from 3 to 30 letters")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @Size(min = 3, max = 30, message = "Last name must be from 3 to 30 letters")
    @NotNull
    private String lastName;
    @NotNull
    @Email(message = "invalid email")
    @Column(unique = true)
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$",message = "invalid password")
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ROLE role;
    @ManyToMany(mappedBy = "users")
    private List<Marathon> marathons;
    @OneToMany(mappedBy = "trainee")
    private List<Progress> progresses;
}
