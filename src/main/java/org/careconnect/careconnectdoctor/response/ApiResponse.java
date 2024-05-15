package org.careconnect.careconnectdoctor.response;

import lombok.Data;

@Data
public class ApiResponse {

    private Object data;

    private Object error;
}
