package org.careconnect.careconnectdoctor.config.feignproxy;


import org.careconnect.careconnectcommon.response.ApiResponse;
import org.careconnect.careconnectdoctor.dto.RequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@FeignClient("care-connect-booking")
public interface BookingFeign {
    @PostMapping("/checkup")
    ApiResponse getDetailsForCheckUp(RequestDto requestDto);
}
