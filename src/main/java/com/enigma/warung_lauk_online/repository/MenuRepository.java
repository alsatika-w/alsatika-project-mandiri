package com.enigma.warung_lauk_online.repository;

import com.enigma.warung_lauk_online.dto.response.MenuResponse;
import com.enigma.warung_lauk_online.entity.Customer;
import com.enigma.warung_lauk_online.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM m_menu WHERE menu_name = :name")
    Menu findMenuByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE m_menu SET menu_name = :name, menu_price = :price WHERE id = :id")
    void updateMenu(@Param("id") String id, @Param("name") String name, @Param("price") Integer price);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM m_menu where id = :id")
    void deleteMenuById(@Param("id") String id);
}
