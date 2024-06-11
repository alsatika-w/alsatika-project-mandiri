package com.enigma.warung_lauk_online.service;

import com.enigma.warung_lauk_online.dto.request.UpdateMenuRequest;
import com.enigma.warung_lauk_online.dto.response.MenuResponse;
import com.enigma.warung_lauk_online.entity.Customer;
import com.enigma.warung_lauk_online.entity.Menu;

import java.util.List;

public interface MenuService {
    Menu create(Menu menu);
    List<Menu> getAllMenu();
    Menu findByName(String name);
    Menu getById(String id);
    void update(UpdateMenuRequest menuRequest);
    void deleteMenuById(String id);
}
