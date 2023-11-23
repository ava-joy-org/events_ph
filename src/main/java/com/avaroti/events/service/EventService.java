package com.avaroti.events.service;

import com.avaroti.events.model.Attendance;
import com.avaroti.events.model.Events;
import com.avaroti.events.repository.EventRepository;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repo;

    private final MongoTemplate template;


    public Events create(Events event) {
//        event.setUpdate_status(false);
        event.setLocation(event.getLocation().toUpperCase());
        event.setAttendance(event.getAttendanceList() == null ? 0 : event.getAttendanceList().size());
        return repo.save(event);
    }

    public List<Events> getAll(){
        return repo.findAllByOOrderByDate();
    }
    public Events getEventById(String id) {
        return repo.findById(id).orElse(new Events());
    }

    public void attendance(Attendance attendance, String id){
        Events ev = repo.findById(id).orElse(null);
        if(ev!=null) {
            List<Attendance> attendanceList = ev.getAttendanceList() == null ? new ArrayList<>() : ev.getAttendanceList();
            attendanceList.add(attendance);
            ev.setAttendance(ev.getAttendanceList() == null ? 0 : ev.getAttendanceList().size() + 1);
            ev.setAttendanceList(attendanceList);
            repo.save(ev);
        }
    }

    public Events update(String id, Events updatedEvent){
        System.out.println("EVENT ID: " +  id);
        Events ev = repo.findById(id).orElse(null);
        if(ev != null) {
           ev.setDate(updatedEvent.getDate());
           ev.setDescription(updatedEvent.getDescription());
           ev.setLocation(updatedEvent.getLocation());
           ev.setStart_time(updatedEvent.getStart_time());
           ev.setName(updatedEvent.getName());
           ev.setImageSrc(updatedEvent.getImageSrc());
           return repo.save(ev);
        }
        return null;
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public List<Events> search(String keyword){
        return repo.searchByName(keyword);
    }
}
