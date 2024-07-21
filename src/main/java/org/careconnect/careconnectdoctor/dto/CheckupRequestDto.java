package org.careconnect.careconnectdoctor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckupRequestDto {

    @NotNull(message = "Patient Id can't be null")
    private long patientId;

    @NotNull(message = "Doctor Id can't be null")
    private long doctorId;

    @Future(message = "Appointment Date should be in future")
    @NotNull(message = "Appointment Date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointmentDate;

    private long appointmentId;

    private String checkupStatus;

    @NotEmpty(message = "Prescription can't be null")
    private String prescription;

    @NotEmpty(message = "ExtraComment can't be null")
    private String extraComment;
}
