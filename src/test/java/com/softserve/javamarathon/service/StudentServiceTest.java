package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.ROLE;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.repository.UserRepository;
import com.softserve.javamarathon.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User student = User.builder()
        .id(1L)
        .role(ROLE.ROLE_TRAINEE)
        .email("testUser@gmail.com")
        .lastName("test")
        .firstName("ftest")
        .password("Qwert1234")
        .build();

    @Test
    public void findByIdTest() {
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(student));
        User findByIdEntity = userService.getUserById(1L);
        assertEquals(1L, (long) findByIdEntity.getId());
    }

    @Test
    public void findByIdFailedTest() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoEntityException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void deleteTest() {
        when(userRepository.findById(student.getId())).thenReturn(Optional.of(student));
        userService.deleteUserById(student.getId());
        verify(userRepository).delete(student);

    }

    @Test
    public void saveTest() {
        when(userRepository.save(student)).thenReturn(student);
        assertEquals(student, userService.createOrUpdateUser(student));
    }

    @Test
    public void findByRoleTest() {
        List<User> users = new ArrayList<>(Collections.singletonList(student));
        when(userRepository.findAllByRole(ROLE.ROLE_TRAINEE)).thenReturn(users);
        assertEquals(users.get(0), userService.getAllByRole("TRAINEE").get(0));
    }
}
