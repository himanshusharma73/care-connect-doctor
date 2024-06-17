package org.careconnect.careconnectdoctor.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.careconnect.careconnectcommon.response.ApiResponse;
import org.careconnect.careconnectdoctor.config.feignproxy.BookingFeign;
import org.careconnect.careconnectdoctor.config.feignproxy.PatientFeign;
import org.careconnect.careconnectdoctor.dto.RequestDto;
import org.careconnect.careconnectcommon.entity.Checkup;
import org.careconnect.careconnectcommon.exception.BookingDtoException;
import org.careconnect.careconnectdoctor.repo.CheckUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
    private PatientFeign patientFeign;

    @Autowired
    private CheckUpRepo checkUpRepo;

    @Autowired
    private BookingFeign bookingFeign;

    @PostMapping("/patient/checkup")
    public ResponseEntity<ApiResponse> postCheckUp(@RequestBody RequestDto requestDto){
        try {
            ApiResponse apiResponse= bookingFeign.getDetailsForCheckUp(requestDto);
            if (apiResponse != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                Checkup checkup = objectMapper.convertValue(apiResponse.getData(), Checkup.class);
                checkup.setCheckupStatus("completed");
                checkup.setPrescription(requestDto.getPrescription());
                checkup.setExtraComment(requestDto.getExtraComment());
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
            throw new BookingDtoException(jsonNode.get("error").get("errorDetails").asText());
        }
        throw new BookingDtoException("Some error occurred try again");
    }

    @PostMapping("/checkups")
    public List<Checkup> getAllDetails(){
         return  checkUpRepo.findAll();
    }
}
