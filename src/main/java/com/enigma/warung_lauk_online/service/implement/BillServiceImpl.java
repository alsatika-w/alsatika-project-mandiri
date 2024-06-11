package com.enigma.warung_lauk_online.service.implement;

import com.enigma.warung_lauk_online.dto.request.BillRequest;
import com.enigma.warung_lauk_online.dto.response.BillDetailResponse;
import com.enigma.warung_lauk_online.dto.response.BillResponse;
import com.enigma.warung_lauk_online.entity.Bill;
import com.enigma.warung_lauk_online.entity.BillDetail;
import com.enigma.warung_lauk_online.entity.Customer;
import com.enigma.warung_lauk_online.entity.Menu;
import com.enigma.warung_lauk_online.repository.BillRepository;
import com.enigma.warung_lauk_online.service.BillDetailService;
import com.enigma.warung_lauk_online.service.BillService;
import com.enigma.warung_lauk_online.service.CustomerService;
import com.enigma.warung_lauk_online.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final BillDetailService billDetailService;
    private final CustomerService customerService;
    private final MenuService menuService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BillResponse create(BillRequest billRequest) {
        Customer customer = customerService.getById(billRequest.getCustomerId());

        Bill bill = Bill.builder()
                .customer(customer)
                .transDate(new Date())
                .build();
        billRepository.saveAndFlush(bill);

        List<BillDetail> billDetails = billRequest.getBillDetails().stream().map(billDetailRequest ->
        {
            Menu menu = menuService.getById(billDetailRequest.getMenuId());

            return BillDetail.builder()
                    .menu(menu)
                    .bill(bill)
                    .quantity(billDetailRequest.getQuantity())
                    .build();
        }).toList();
        billDetailService.createBulk(billDetails);
        bill.setBillDetails(billDetails);
        List<BillDetailResponse> billDetailResponses = billDetails.stream().map(
                detail -> {
                    return BillDetailResponse.builder()
                            .billDetailId(detail.getId())
                            .menuId(detail.getMenu().getId())
                            .menuName(detail.getMenu().getName())
                            .menuPrice(Long.valueOf(detail.getMenu().getPrice()))
                            .qty(detail.getQuantity())
                            .build();
                }
        ).toList();

        BillResponse response = BillResponse.builder()
                .billId(bill.getId())
                .customerId(bill.getCustomer().getId())
                .customerName(bill.getCustomer().getName())
                .customerAddress(bill.getCustomer().getAddress())
                .customerPhone(bill.getCustomer().getMobilePhoneNo())
                .customerMember(String.valueOf(bill.getCustomer().getMember()))
                .transDate(bill.getTransDate())
                .billDetails(billDetailResponses)
                .build();
        return response;

    }
@Transactional(readOnly = true)
    @Override
    public List<BillResponse> getAll() {
    List<Bill> bills = billRepository.findAll();
    return bills.stream().map(bill -> {
        List<BillDetailResponse> billDetailResponses = bill.getBillDetails().stream().map(detail -> {
            return BillDetailResponse.builder()
                    .billDetailId(detail.getId())
                    .menuId(detail.getMenu().getId())
                    .menuName(detail.getMenu().getName())
                    .menuPrice(Long.valueOf(detail.getMenu().getPrice()))
                    .qty(detail.getQuantity())
                    .build();
        }).toList();

        return BillResponse.builder()
                .billId(bill.getId())
                .customerId(bill.getCustomer().getId())
                .customerName(bill.getCustomer().getName())
                .customerAddress(bill.getCustomer().getAddress())
                .customerPhone(bill.getCustomer().getMobilePhoneNo())
                .customerMember(String.valueOf(bill.getCustomer().getMember()))
                .transDate(bill.getTransDate())
                .billDetails(billDetailResponses)
                .build();
    }).toList();
}
}
