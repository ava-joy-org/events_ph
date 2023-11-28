package com.avaroti.events.controller;

import com.avaroti.events.configs.WebClientConfig;
import com.avaroti.events.model.Events;
import com.avaroti.events.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final EventService service;
    private final WebClientConfig webClientConfig;

    @GetMapping("/")
    public String top(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, @ModelAttribute("keyword") String keyword) {
        log.info("Home page display");
        List<Events> open = service.getAllValid();
//        List<Events> closed = service.getAllInvalid();
        model.addAttribute("events", open);
//        model.addAttribute("closed", closed);
        model.addAttribute("keyword", keyword);

        String countryName = "philippines";
        String url = "https://restcountries.com/v3.1/name/"+ countryName;
        RestTemplate temp = new RestTemplate();
        return "index";
    }

    @GetMapping("/join")
    public String join(Model model, @RequestParam("id") String id) {
        log.info("Joining event");
        return "redirect:/newAttendance?ev_id=" + id;
    }

    @GetMapping("/visitToUpdate/{id}")
    public String visitToUpdate(Model model, @PathVariable("id") String id){
        log.info("Updating eventID: {}", id);
        Events ev = service.getEventById(id, 0);
        model.addAttribute("events", ev);
        model.addAttribute("update_id", id);
        model.addAttribute("update_status", true);
        return "new_event";
    }

    @PostMapping("/update")
    public String update(Model model, @RequestParam("ev_id") String id, @ModelAttribute("events") Events event) {
        Events ev = service.update(id, event);
        model.addAttribute("events", ev);
        model.addAttribute("update_status", false);
        log.info("Updated eventID: {}", ev.getId());
        return "redirect:/home";
    }

    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") String id){
        log.info("Deleting eventID: {}", id);
        service.delete(id);
        return "redirect:/home";
    }

    @GetMapping("/search")
    public String search(Model model,@RequestParam("searchWord") String keyword){
        log.info("Searching for keyword {}", keyword);
        List<Events> ev = service.search(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("events", ev);
        model.addAttribute("searchCount", ev.size());
        return "search_result";
    }


}
