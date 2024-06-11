package com.enigma.warung_lauk_online.entity;

import com.enigma.warung_lauk_online.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = ConstantTable.MENU)
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "menu_name")
    private String name;

    @Column(name = "menu_price", nullable = false, updatable = false)
    private Integer price;

}
