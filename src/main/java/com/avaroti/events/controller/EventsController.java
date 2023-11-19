package com.avaroti.events.controller;

import com.avaroti.events.model.Events;
import com.avaroti.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class EventsController {

    private final EventService service;

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Events event) {
        Events events = service.create(event);
        model.addAttribute("events", events);
        System.out.println("ID: "+events.getId());
        return "redirect:/home";
    }

    @PostMapping("/attendance")
    public String attendance(Model model, @ModelAttribute Events joinMember){
        service.attendance(joinMember);
        model.addAttribute("ev_data", joinMember);
        return "redirect:/join?id="+ joinMember.getId();
    }

//    @PostMapping("/submit-event")
//    public String submitUser(Events events, Model model) {
//        // Process the user object (save to database, perform business logic, etc.)
//        // Redirect to a success page or another page
//        return "redirect:/index";
//    }
}
