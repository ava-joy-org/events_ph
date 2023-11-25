package com.avaroti.events.service;

import com.avaroti.events.model.Attendance;
import com.avaroti.events.model.Events;
import com.avaroti.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repo;

    private final MongoTemplate template;


    public Events create(Events event) {
//        String inputDate = event.getDate();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate date = LocalDate.parse(inputDate, formatter);
//        DayOfWeek dayOfWeek = date.getDayOfWeek();
        event.setDate(event.getDate());
        event.setLocation(event.getLocation().toUpperCase());
        event.setView_count(0);
        event.setAttendance(event.getAttendanceList() == null ? 0 : event.getAttendanceList().size());
        return repo.save(event);
    }

    public List<Events> getAllValid(){
        List<Events> evs =repo.findAllByOOrderByDate();
        evs  = evs.stream().filter(e ->
                LocalDateTime.of(
                        LocalDate.parse(e.getDate()),
                        LocalTime.parse(e.getStart_time())).isAfter(LocalDateTime.now()))
                .toList();
        return evs;
    }

    public List<Events> getAllInvalid(){
        List<Events> evs =repo.findAllByOOrderByDate();
        evs  = evs.stream().filter(e ->
                        LocalDateTime.of(
                                LocalDate.parse(e.getDate()),
                                LocalTime.parse(e.getStart_time())).isBefore(LocalDateTime.now()))
                .toList();
        return evs;
    }
    public Events getEventById(String id) {
        Events ev =  repo.findById(id).orElse(new Events());
        ev.setView_count(ev.getView_count()+1);
        repo.save(ev);
        return ev;
    }

    public void attendance(Attendance attendance, String id){
        int count = 0;
        Events ev = repo.findById(id).orElse(null);
        if(ev!=null) {
            if(ev.getAttendanceList()!= null) {
                count = ev.getAttendanceList().size();
            }
            List<Attendance> attendanceList = ev.getAttendanceList()== null ? new ArrayList<>() : ev.getAttendanceList();
            attendanceList.add(attendance);
            ev.setAttendance(count+1);
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
