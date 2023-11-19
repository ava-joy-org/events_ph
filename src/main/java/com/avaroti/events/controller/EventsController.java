package com.avaroti.events.controller;

import com.avaroti.events.model.Events;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventsController {

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute Events event) {

        return "index";
    }
}
