package org.careconnect.careconnectdoctor.config.feignproxy;


import org.careconnect.careconnectdoctor.dto.RequestDto;
import org.careconnect.careconnectdoctor.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@FeignClient("care-connect-booking")
public interface BookingFeign {
    @PostMapping("/checkup")
    public ApiResponse getDetailsForCheckUp(RequestDto requestDto);
}
