package org.careconnect.careconnectdoctor.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.validation.Valid;
import org.careconnect.careconnectcommon.exception.DuplicateResourceException;
import org.careconnect.careconnectcommon.response.ApiResponse;
import org.careconnect.careconnectdoctor.config.feignproxy.BookingFeign;
import org.careconnect.careconnectdoctor.config.feignproxy.PatientFeign;
import org.careconnect.careconnectdoctor.dto.CheckupRequestDto;
import org.careconnect.careconnectcommon.entity.Checkup;
import org.careconnect.careconnectcommon.exception.BookingDtoException;
import org.careconnect.careconnectdoctor.repo.CheckUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorController {

    @Autowired
    private PatientFeign patientFeign;

    @Autowired
    private CheckUpRepo checkUpRepo;

    @Autowired
    private BookingFeign bookingFeign;

    @PostMapping("/patient/checkup")
    public ResponseEntity<ApiResponse> postCheckUp(@Valid @RequestBody CheckupRequestDto checkupRequestDto){
        try {
            ApiResponse apiResponse= bookingFeign.getDetailsForCheckUp(checkupRequestDto);
            if (apiResponse != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                Checkup checkup = objectMapper.convertValue(apiResponse.getData(), Checkup.class);
                if (checkUpRepo.existsByAppointmentId(checkup.getAppointmentId())){
                    throw new DuplicateResourceException("The checkup already done");
                }
                checkup.setCheckupStatus("completed");
                checkup.setPrescription(checkupRequestDto.getPrescription());
                checkup.setExtraComment(checkupRequestDto.getExtraComment());
                Checkup save = checkUpRepo.save(checkup);
                apiResponse.setData(save);
                return ResponseEntity.ok(apiResponse);
            }
        }catch (FeignException e){
            String errorMessage = e.contentUTF8();
                ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(errorMessage);

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            assert jsonNode != null;
            throw new BookingDtoException(jsonNode.get("error").get("errorDetails").asText());
        }
        throw new BookingDtoException("Some error occurred try again");
    }

    @PostMapping("/checkups")
    public ResponseEntity<ApiResponse> getAllDetails(){
         ApiResponse apiResponse = new ApiResponse();
         apiResponse.setData(checkUpRepo.findAll());
         return ResponseEntity.ok(apiResponse);
    }
}
