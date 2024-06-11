package com.enigma.warung_lauk_online.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BillDetailResponse {
    private String billDetailId;
    private String menuId;
    private String menuName;
    private Long menuPrice;
    private Integer qty;
}
