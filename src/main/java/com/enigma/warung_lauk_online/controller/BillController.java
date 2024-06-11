package com.enigma.warung_lauk_online.controller;

import com.enigma.warung_lauk_online.constant.APIUrl;
import com.enigma.warung_lauk_online.dto.request.BillRequest;
import com.enigma.warung_lauk_online.dto.response.BillResponse;
import com.enigma.warung_lauk_online.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.BILL_API)
public class BillController {
    private final BillService billService;

    @PostMapping
    public BillResponse createNewBill(@RequestBody BillRequest billRequest){
        return billService.create(billRequest);
    }

    @GetMapping
    public List<BillResponse> getAllBill(){
        return billService.getAll();
    }

}
