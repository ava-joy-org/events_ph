package com.avaroti.events.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "new_event";
    }

    @GetMapping("/join")
    public String join(Model model) {
        return "join_now";
    }
}
