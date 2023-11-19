package com.avaroti.events.controller;

import com.avaroti.events.model.Events;
import com.avaroti.events.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EventsController {

    private final EventService service;

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Events event) {
        Events events = service.create(event);
        model.addAttribute("events", events);
        log.info("Redirecting to HOME: Event ID: {}", events.getId());
        return "redirect:/home";
    }

    @PostMapping("/attendance")
    public String attendance(Model model, @ModelAttribute Events joinMember){
        service.attendance(joinMember);
        model.addAttribute("ev_data", joinMember);
        return "redirect:/join?id="+ joinMember.getId();
    }
}
