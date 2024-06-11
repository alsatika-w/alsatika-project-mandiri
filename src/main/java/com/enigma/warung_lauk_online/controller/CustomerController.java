package com.enigma.warung_lauk_online.controller;

import com.enigma.warung_lauk_online.constant.APIUrl;
import com.enigma.warung_lauk_online.constant.ResponseMessage;
import com.enigma.warung_lauk_online.dto.request.UpdateCustomerRequest;
import com.enigma.warung_lauk_online.dto.response.CommonResponse;
import com.enigma.warung_lauk_online.dto.response.CustomerResponse;
import com.enigma.warung_lauk_online.entity.Customer;
import com.enigma.warung_lauk_online.service.CustomerService;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> createNewCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.create(customer);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newCustomer)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> getAllCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "mobilePhoneNo",required = false) String phone,
            @RequestParam(name = "member", required = false) Boolean member
    ) {
        List<Customer> allCustomer = customerService.getAllCustomer();

        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(allCustomer)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_NAME)
    public ResponseEntity<CommonResponse<Customer>> getByName(@PathVariable String name) {
        Customer customer = customerService.findByName(name);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<CustomerResponse>> updateCustomer(@RequestBody UpdateCustomerRequest customerRequest) {
        CustomerResponse updateCustomer = customerService.update(customerRequest);

        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(updateCustomer)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<String>> updateStatusMemberById(
            @PathVariable String id,
            @RequestParam(name = "isMember") Boolean member
    ) {
        customerService.updateStatusMemberById(id, member);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteCustomerById(@PathVariable String id) {
        customerService.deleteCustById(id);

        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }
}
