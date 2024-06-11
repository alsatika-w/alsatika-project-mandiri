package com.enigma.warung_lauk_online.service.implement;

import com.enigma.warung_lauk_online.dto.request.UpdateCustomerRequest;
import com.enigma.warung_lauk_online.dto.response.CustomerResponse;
import com.enigma.warung_lauk_online.entity.Customer;
import com.enigma.warung_lauk_online.repository.CustomerRepository;
import com.enigma.warung_lauk_online.service.CustomerService;
import com.enigma.warung_lauk_online.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Customer create(Customer customer) {
        validationUtil.validate(customer);

        return customerRepository.saveAndFlush(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found"));
    }

    @Transactional(readOnly = true)
    private CustomerResponse parseCustomerToCustomerResponse(Customer customer){

        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .mobilePhoneNo(customer.getMobilePhoneNo())
                .address(customer.getAddress())
                .isMember(customer.getMember())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse update(UpdateCustomerRequest customerRequest) {
        validationUtil.validate(customerRequest);

        Customer currentCustomer = findByIdOrThrowNotFound(customerRequest.getId());

        currentCustomer.setName(customerRequest.getName());
        currentCustomer.setAddress(customerRequest.getAddress());
        currentCustomer.setMobilePhoneNo(customerRequest.getPhone());
        customerRepository.saveAndFlush(currentCustomer);

        return parseCustomerToCustomerResponse(currentCustomer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatusMemberById(String id, Boolean member) {
      validationUtil.validate(id);
      findByIdOrThrowNotFound(id);
      customerRepository.updateMember(id, member);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCustById(String id) {
        validationUtil.validate(id);
        findByIdOrThrowNotFound(id);
        customerRepository.deleteCustomerById(id);
    }
}
