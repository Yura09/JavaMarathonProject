package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.entity.enums.ROLE;
import com.softserve.javamarathon.repository.UserRepository;
import com.softserve.javamarathon.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void findById() {
        User student = new User();
        student.setId(1L);
        student.setRole(ROLE.TRAINEE);
        student.setEmail("testUser@gmail.com");
        student.setFirstName("firstName1");
        student.setLastName("lastName1");
        student.setPassword("Qwert1234");
        when(userRepository.findById(anyLong())).thenReturn(java.util.Optional.of(student));
        User findByIdEntity = userService.getUserById(1L);
        assertEquals(1L, (long)findByIdEntity.getId());
    }
}
