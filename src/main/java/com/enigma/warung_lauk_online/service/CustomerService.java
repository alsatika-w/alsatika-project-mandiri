package com.enigma.warung_lauk_online.service;

import com.enigma.warung_lauk_online.dto.request.UpdateCustomerRequest;
import com.enigma.warung_lauk_online.dto.response.CustomerResponse;
import com.enigma.warung_lauk_online.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    List<Customer> getAllCustomer();
    Customer findByName(String name);
    Customer getById(String id);
    CustomerResponse update(UpdateCustomerRequest customerRequest);
    void updateStatusMemberById(String id, Boolean member);
    void deleteCustById(String id);
}
