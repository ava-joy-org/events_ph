package com.avaroti.events.service;

import com.avaroti.events.model.Events;
import com.avaroti.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository repo;

    public Events create(Events event) {
        event.setStatus(true);
        event.setAttendance(0L);
        return repo.save(event);
    }

    public List<Events> getAll(){
        return repo.findAll();
    }
    public Events getEventById(String id) {
        return repo.findById(id).orElse(new Events());
    }

    public void attendance(Events event){
        Events ev = repo.findById(event.getId()).orElse(null);
        if(ev != null) {
            log.info("EVENT DATA: {} : [{}, {}]",ev.getId(), ev.getName(), ev.getLocation());
            long getCount = ev.getAttendance();
            log.info("Updating data - eventID: {}", event.getId());
            ev.setAttendance(getCount+1L);
            repo.save(ev);
        }
    }
}
