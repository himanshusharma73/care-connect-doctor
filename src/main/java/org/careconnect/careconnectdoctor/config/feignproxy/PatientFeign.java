package org.careconnect.careconnectdoctor.config.feignproxy;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@FeignClient("care-connect-patient")
public interface PatientFeign {
    @GetMapping("/illnesses/patients/{patient_Id}")
    public ResponseEntity<ApiResponse> illnessHistory(@PathVariable long patient_Id);
}
