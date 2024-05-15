package org.careconnect.careconnectdoctor.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.careconnect.careconnectdoctor.config.feignproxy.PatientFeign;
import org.careconnect.careconnectcommon.exception.BookingDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @Autowired
    PatientFeign patientFeign;

    @GetMapping("/illnesses/patients/{patient_Id}")
    public ResponseEntity getIllness(Long patient_Id){
        try {
            return patientFeign.illnessHistory(patient_Id);
        }
        catch (FeignException e){
            String errorMessage = e.contentUTF8();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(errorMessage);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new BookingDtoException(jsonNode.get("error").get("errorDetails").asText());
    }
    }
}
