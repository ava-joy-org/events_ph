package com.avaroti.events.service;

import com.avaroti.events.model.Events;
import com.avaroti.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repo;

    public Events create(Events event) {
        return repo.save(event);
    }

    public List<Events> getAll(){
        return repo.findAll();
    }
    public Optional<Events> getEventById(String id) {
        return repo.findById(id);
    }

    public void attendance(Events event){
        Optional<Events> ev = repo.findById(event.getId());
        long getCount = ev.map(Events::getAttendance).orElse(0L);
        ev.get().setAttendance(getCount+1);
        repo.save(ev.get());
    }
}
