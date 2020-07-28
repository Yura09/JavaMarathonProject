package com.softserve.javamarathon.controller;

import com.softserve.javamarathon.entity.enums.ROLE;
import com.softserve.javamarathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getStudentsFromMarathon(@PathVariable("id") Long marathonId, Model model) {
        model.addAttribute("students", userService.getAll().stream().filter(student -> student.getRole().equals(ROLE.TRAINEE)).filter(marathon -> marathon.getMarathons().stream().anyMatch(m -> m.getId().equals(marathonId))).collect(Collectors.toList()));
        return "student/list-students";
    }

    public String getAllStudents(Model model) {
        model.addAttribute("students", userService.getAll());
        return "student/list-students";
    }
}
