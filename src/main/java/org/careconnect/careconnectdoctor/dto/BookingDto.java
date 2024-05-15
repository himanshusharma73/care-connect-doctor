package org.careconnect.careconnectdoctor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDto {
    
    private Long doctorId;

    private Long patientId;

    private LocalDate appointmentDate;

    private LocalTime appointmentStartTime;

    private String specialization;
}
