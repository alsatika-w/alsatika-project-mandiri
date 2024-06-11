package com.enigma.warung_lauk_online.service;

import com.enigma.warung_lauk_online.dto.request.BillRequest;
import com.enigma.warung_lauk_online.dto.response.BillResponse;

import java.util.List;

public interface BillService {
    BillResponse create(BillRequest billRequest);
    List<BillResponse> getAll();
}
