package com.avaroti.events.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    private String name;

    private String email;

    private String contact_no;

    private boolean status;

}
