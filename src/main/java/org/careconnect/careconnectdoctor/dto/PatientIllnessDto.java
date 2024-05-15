package org.careconnect.careconnectdoctor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientIllnessDto {

    private long illnessId;

    private PatientDto patient;

    private List<String> illness;

    private String description;

    private LocalDate illnessDate;
}
