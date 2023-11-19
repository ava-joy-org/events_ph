package com.avaroti.events.model;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "attendance")
public class Attendance {
    @Id
    private String id;

    private String name;

    private String email;

    private String contact_no;

}
