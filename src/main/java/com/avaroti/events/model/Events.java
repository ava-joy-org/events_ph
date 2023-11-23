package com.avaroti.events.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "event_list")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String location;

    private String date;

    private String start_time;

    private int duration;

    private long attendance;

    private String imageSrc;

    private String description;

    private List<Attendance> attendanceList;


}
