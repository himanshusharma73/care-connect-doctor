package org.careconnect.careconnectdoctor.dto;

import lombok.Data;

@Data
public class DoctorDto {

    private Long doctorId;

    private Name name;

    private String email;

    private long mobileNo;

    private String gender;

    private String licenseNumber;

    private Address address;

    private long adharNo;

    private String bloodGroup;

    private String maritalStatus;

    private Specialization specialization;

}
