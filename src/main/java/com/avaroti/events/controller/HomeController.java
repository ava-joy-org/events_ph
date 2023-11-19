package com.avaroti.events.controller;

import com.avaroti.events.model.Events;
import com.avaroti.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final EventService service;

    @GetMapping("/home")
    public String home(Model model) {
        List<Events> displayEvents = service.getAll();
        model.addAttribute("eventsDisp", displayEvents);
        return "index";
    }


    @GetMapping("/create")
    public String create(Model model, Events event) {
        model.addAttribute("events", event);
        return "new_event";
    }

    @GetMapping("/join")
    public String join(Model model, @ModelAttribute Events eventsJoin) {
        model.addAttribute("ev_data", service.getEventById(eventsJoin.getId()));
        return "event_page";
    }
}
