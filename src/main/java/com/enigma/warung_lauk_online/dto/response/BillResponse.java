package com.enigma.warung_lauk_online.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class BillResponse {
    private String billId;
    private String customerId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String customerMember;
    private Date transDate;
    private List<BillDetailResponse> billDetails;
}
