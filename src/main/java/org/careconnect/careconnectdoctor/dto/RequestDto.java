package org.careconnect.careconnectdoctor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {

    private long patientId;

    private long doctorId;

    private LocalDate appointmentDate;

    private long appointmentId;

    private String checkupStatus;

    private String prescription;

    private String extraComment;
}
