package com.softserve.javamarathon.controller;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.entity.User;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.service.MarathonService;
import com.softserve.javamarathon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/students")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);
    private UserService userService;
    private MarathonService marathonService;

    @Autowired
    public void setMarathonService(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getStudentsFromMarathon(@PathVariable("id") Long marathonId, Model model) {
        model.addAttribute("marathonId", marathonId);
        model.addAttribute("students", userService.getAllByRole("TRAINEE").stream().filter(marathon -> marathon.getMarathons().stream().anyMatch(m -> m.getId().equals(marathonId))).collect(Collectors.toList()));
        return "student/list-students";
    }

    @GetMapping
    public String getAllStudents(Model model) {
        logger.info("get all students");
        model.addAttribute("students", userService.getAllByRole("TRAINEE"));
        return "student/list-students";
    }

    @GetMapping("/{id}/add")
    public String addToMarathonForm(@PathVariable("id") Long marathonId, Model model) {
        model.addAttribute("marathonId", marathonId);
        model.addAttribute("student", new User());
        return "student/add-student";
    }

    @GetMapping("/{marathonId}/edit/{id}")
    public String editForm(@PathVariable("marathonId") Long marathonId, @PathVariable("id") Long id, Model model) {
        try {
            User student = userService.getUserById(id);
            model.addAttribute("marathonId", marathonId);
            model.addAttribute("student", student);
            return "student/edit-student";
        } catch (NoEntityException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/{id}/add")
    public String addStudentToMarathon(@PathVariable("id") @ModelAttribute("marathonId") Long marathonId, @Valid @ModelAttribute("student") User student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("marathonId", marathonId);
            return "student/add-student";
        }
        Marathon marathon = marathonService.getMarathonById(marathonId);
        User newStudent = userService.createOrUpdateUser(student);
        userService.addUserToMarathon(newStudent, marathon);


        return "redirect:/students/" + marathonId;
    }

    @PostMapping("/{marathonId}/edit/{id}")
    public String editStudent(@PathVariable("marathonId") @ModelAttribute("marathonId") Long marathonId, @PathVariable("id") @ModelAttribute("id") Long id, @Valid @ModelAttribute("student") User student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("marathonId", marathonId);
            model.addAttribute("id", id);
            return "student/edit-student";
        }
        userService.createOrUpdateUser(student);
        return "redirect:/students/" + marathonId;
    }

    @GetMapping("/{marathonId}/delete/{id}")
    public String deleteStudentFromMarathon(@PathVariable("marathonId") Long marathonId, @PathVariable("id") Long id, Model model) {
        try {
            userService.deleteUserById(id);
            return "redirect:/students/" + marathonId;
        } catch (NoEntityException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
