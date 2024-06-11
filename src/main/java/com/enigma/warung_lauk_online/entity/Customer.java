package com.enigma.warung_lauk_online.entity;

import com.enigma.warung_lauk_online.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.CUSTOMER)
public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;

        @Column(name = "customer_name")
        private String name;

        @Column(name = "mobile_phone_no")
        private String mobilePhoneNo;

        @Column(name = "address")
        private String address;

        @Column(name = "is_member")
        private Boolean member;
}
