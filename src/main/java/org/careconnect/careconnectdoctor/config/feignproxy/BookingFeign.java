package org.careconnect.careconnectdoctor.config.feignproxy;


import org.careconnect.careconnectcommon.response.ApiResponse;
import org.careconnect.careconnectdoctor.dto.CheckupRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("care-connect-booking")
public interface BookingFeign {
    @PostMapping("/checkup")
    ApiResponse getDetailsForCheckUp(CheckupRequestDto checkupRequestDto);
}
