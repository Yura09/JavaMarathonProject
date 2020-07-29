package com.softserve.javamarathon.controller;

import com.softserve.javamarathon.entity.Marathon;
import com.softserve.javamarathon.exception.NoEntityException;
import com.softserve.javamarathon.service.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/marathons")
public class MarathonController {
    private MarathonService marathonService;

    @Autowired
    public void setMarathonService(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @GetMapping
    public String getAllMarathons(Model model) {
        model.addAttribute("marathons", marathonService.getAll());
        return "marathon/list-marathons";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        try {
            Marathon marathon = marathonService.getMarathonById(id);
            model.addAttribute("marathon", marathon);
            return "marathon/edit-marathon";
        } catch (NoEntityException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("marathon", new Marathon());
        return "marathon/create-marathon";
    }

    @PostMapping("/edit/{id}")
    public String editMarathon(@PathVariable("id") Long id, @Valid Marathon marathon, BindingResult result) {
        if (result.hasErrors()) {
            return "marathon/edit-marathon";
        }
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @PostMapping("/create")
    public String createMarathon(@Valid Marathon marathon, BindingResult result) {
        if (result.hasErrors()) {
            return "marathon/create-marathon";
        }
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteMarathon(@PathVariable("id") Long id, Model model) {
        try {
            marathonService.deleteMarathonById(id);
            return "redirect:/marathons";
        } catch (NoEntityException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
