package com.avaroti.events.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "event_list")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Validated
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String location;

//    @Past(message = "date must not be before today.")
//    @FutureOrPresent(message = "Date must be today or in the future")
    private String date;

    private String start_time;

    private int duration;

    private long attendance;

    private String imageSrc;

    private String description;

    private List<Attendance> attendanceList;

    private boolean err;

    private long view_count;


}
