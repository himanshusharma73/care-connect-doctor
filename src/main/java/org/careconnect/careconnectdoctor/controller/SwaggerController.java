package org.careconnect.careconnectdoctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/swagger-ui.html";
    }
}
