package com.enigma.warung_lauk_online.service;

import com.enigma.warung_lauk_online.entity.BillDetail;
import com.enigma.warung_lauk_online.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BillDetailService {
    List<BillDetail> createBulk(List<BillDetail> transactionDetails);
}
