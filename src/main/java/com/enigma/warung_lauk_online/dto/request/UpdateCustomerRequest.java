package com.enigma.warung_lauk_online.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerRequest {
    private String id;
    private String name;
    private String Phone;
    private String address;
    private Boolean member;
}
