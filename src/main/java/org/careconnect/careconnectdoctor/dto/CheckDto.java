package org.careconnect.careconnectdoctor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CheckDto {
    private long checkupId;

    private long appointmentId;

    private String checkupStatus;

    private String prescription;

    private String extraComment;

}
