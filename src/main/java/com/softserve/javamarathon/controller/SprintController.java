package com.softserve.javamarathon.controller;

import com.softserve.javamarathon.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sprints")
public class SprintController {
    private SprintService sprintService;

    @Autowired
    public void setSprintService(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping("/{id}")
    public String getSprintsFromMarathon(@PathVariable("id") Long marathonId, Model model) {
        model.addAttribute("marathonId", marathonId);
        model.addAttribute("sprints", sprintService.getSprintsByMarathonId(marathonId));
        return "sprint/list-sprints";
    }
}
