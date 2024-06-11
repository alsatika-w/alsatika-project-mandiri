package com.enigma.warung_lauk_online.repository;

import com.enigma.warung_lauk_online.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM m_customer WHERE customer_name = :name")
    Customer findByName(@Param("name") String name);

   @Modifying
   @Transactional
    @Query(value = "UPDATE m_customer SET is_member = :member WHERE id = :id", nativeQuery = true)
    void updateMember(@Param("id") String id, @Param("member") Boolean member);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM m_customer where id = :id")
    void deleteCustomerById(@Param("id") String id);
}
