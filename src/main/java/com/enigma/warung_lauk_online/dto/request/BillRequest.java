package com.enigma.warung_lauk_online.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BillRequest {
    private String customerId;
    private List<BillDetailRequest> billDetails;
}
