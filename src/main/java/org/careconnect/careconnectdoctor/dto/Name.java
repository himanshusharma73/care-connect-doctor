package org.careconnect.careconnectdoctor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {

    private String firstName;

    private String middleName;

    private String lastName;
}
