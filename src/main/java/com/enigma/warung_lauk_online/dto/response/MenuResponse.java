package com.enigma.warung_lauk_online.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponse {
    private String id;
    private String name;
    private Integer price;
}
