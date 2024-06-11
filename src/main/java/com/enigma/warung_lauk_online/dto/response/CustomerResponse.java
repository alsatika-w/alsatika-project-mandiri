package com.enigma.warung_lauk_online.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String name;
    private String mobilePhoneNo;
    private String address;
    private Boolean isMember;
}
