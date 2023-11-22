package com.avaroti.events.controller;

import com.avaroti.events.model.Events;
import com.avaroti.events.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final EventService service;

    @GetMapping("/home")
    public String home(Model model) {
        log.info("Home page display");
        List<Events> displayEvents = service.getAll();
        model.addAttribute("events", displayEvents);
        return "index";
    }

    @GetMapping("/join")
    public String join(Model model, @RequestParam("id") String id) {
        log.info("Joining event");
        return "redirect:/newAttendance?ev_id=" + id;
    }

    @GetMapping("/visitToUpdate/{id}")
    public String visitToUpdate(Model model, @PathVariable("id") String id){
        log.info("Updating eventID");
        Events ev = service.getEventById(id);
        ev.setUpdate_status(true);
        model.addAttribute("events", ev);
        model.addAttribute("update_id", id);
        model.addAttribute("update_status", true);
        return "new_event";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") String id, @ModelAttribute("events") Events event) {
        model.addAttribute("update_status", false);
        return "redirect:/home";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id){
        log.info("Deleting eventID: {}", id);
        service.delete(id);
        return "redirect:/home";
    }


}
