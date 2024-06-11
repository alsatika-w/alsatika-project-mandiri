package com.enigma.warung_lauk_online.service.implement;

import com.enigma.warung_lauk_online.dto.response.BillDetailResponse;
import com.enigma.warung_lauk_online.entity.BillDetail;
import com.enigma.warung_lauk_online.repository.BillDetailRepository;
import com.enigma.warung_lauk_online.service.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {
    private final BillDetailRepository billDetailRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<BillDetail> createBulk(List<BillDetail> transactionDetails) {
        return billDetailRepository.saveAllAndFlush(transactionDetails);
    }
}
