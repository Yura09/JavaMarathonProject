package com.softserve.javamarathon.repository;

import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.ROLE;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Test
    public void newUserTest() {

        User user = new User();
        user.setRole(ROLE.TRAINEE);
        user.setEmail("newUser@gmail.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("Qwert1234");

        userRepository.save(user);

        User actual = userRepository.findUserByEmail("newUser@gmail.com");
        Assertions.assertEquals("firstName", actual.getFirstName());
    }

    @Test
    public void checkFindByRole() {
        User user = new User();
        user.setRole(ROLE.TRAINEE);
        user.setEmail("newUser@gmail.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("Qwert1234");
        User user1 = new User();
        user1.setRole(ROLE.TRAINEE);
        user1.setEmail("newUser1@gmail.com");
        user1.setFirstName("firstName1");
        user1.setLastName("lastName1");
        user1.setPassword("Qwert1234");
        userRepository.save(user1);
        List<User> actual = userRepository.findAllByRole(ROLE.TRAINEE);
        Assertions.assertEquals(ROLE.TRAINEE, actual.get(0).getRole());
        Assertions.assertEquals(ROLE.TRAINEE, actual.get(1).getRole());
    }
}
