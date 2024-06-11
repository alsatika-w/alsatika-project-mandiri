package com.enigma.warung_lauk_online.repository;

import com.enigma.warung_lauk_online.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, String> {
}
