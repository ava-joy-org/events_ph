package com.avaroti.events.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "event_list")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String location;

    private String date;

    private String start_time;

    private int duration;

    @Transient
    private long attendance;

    @Transient
    private boolean status;

//    private List<Attendance> attendanceList;


}
