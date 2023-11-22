package com.avaroti.events.service;

import com.avaroti.events.model.Attendance;
import com.avaroti.events.model.Events;
import com.avaroti.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository repo;

    public Events create(Events event) {
        event.setUpdate_status(false);
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

    public void update(String id, Events updatedEvent){
        repo.updateEventsById(id, updatedEvent);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
