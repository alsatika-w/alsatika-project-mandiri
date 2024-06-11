package com.enigma.warung_lauk_online.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BillDetailRequest {
    private String menuId;
    private Integer quantity;
}
