package org.careconnect.careconnectdoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EntityScan("org.careconnect.careconnectcommon.entity")
@ComponentScan({"org.careconnect.careconnectcommon","org.careconnect.careconnectdoctor"})
public class CareConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareConnectApplication.class, args);
	}

}
