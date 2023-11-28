package com.avaroti.events.controller;

import com.avaroti.events.model.Attendance;
import com.avaroti.events.model.Events;
import com.avaroti.events.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EventsController {

    private final EventService service;

    @GetMapping("/create")
    public String create(Model model) {
        log.info("Creating new Event");
        model.addAttribute("events", new Events());
        model.addAttribute("update_status", false);
        model.addAttribute("defaultDate", "2023-01-01");
        return "new_event";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute("events") Events event) {
        Events events = service.create(event);
        log.info("Redirecting to Home page. Event created: {}", events.getId());
        return "redirect:/home";
    }

    @GetMapping("/eventDisplay")
    public String displayEvent(Model model, @RequestParam("ev_id") String id) {
        log.info("Display event. EventID: {}", id);
        Events ev = service.getEventById(id, 1);
        String timeEnd = (LocalTime.parse(ev.getStart_time()).plusHours(ev.getDuration())).toString();
        DayOfWeek dayOfWeek = LocalDate.parse(ev.getDate()).getDayOfWeek();
        model.addAttribute("ev_data", ev);
        model.addAttribute("timeEnd", timeEnd);
        model.addAttribute("dayWeek", dayOfWeek.toString().substring(0,3));
        model.addAttribute("attendees",ev.getAttendanceList());

        model.addAttribute("attendee", new Attendance());
        model.addAttribute("ev_id", id);
        return "event_page";
    }

//    @GetMapping("/newAttendance")
//    public String attendance(Model model, @RequestParam("ev_id") String id){
//        log.info("EVENT ID: {}",id);
//        model.addAttribute("attendee", new Attendance());
//        model.addAttribute("ev_id", id);
//        return "attendance";
//    }

    @PostMapping("/submitAttendance")
    public String submitAttendance(Model model, @RequestParam("ev_id") String id,  @ModelAttribute("attendee") Attendance attendance){
        log.info("SUBMIT Event_ID: {}", id);
        service.attendance(attendance, id);
        return "redirect:/eventDisplay?ev_id=" + id;
    }
}
