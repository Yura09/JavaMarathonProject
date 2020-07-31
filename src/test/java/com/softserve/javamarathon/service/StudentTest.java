package com.softserve.javamarathon.service;

import com.softserve.javamarathon.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class StudentTest {
    private MockMvc mockMvc;
    private UserService userService;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void getAllStudentsTest() throws Exception {
        List<User> expected = userService.getAllByRole("TRAINEE");
        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("students"))
                .andExpect(MockMvcResultMatchers.model().attribute("students", expected));

    }
    @Test
    public void addStudentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/students/1/add")
                .param("email", "test@gmail.com")
                .param("firstName", "fName")
                .param("lastName", "lName")
                .param("password", "Password1234")
                .param("role", "TRAINEE"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
