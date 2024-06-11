package com.enigma.warung_lauk_online.service.implement;

import com.enigma.warung_lauk_online.dto.request.UpdateMenuRequest;
import com.enigma.warung_lauk_online.dto.response.MenuResponse;
import com.enigma.warung_lauk_online.entity.Customer;
import com.enigma.warung_lauk_online.entity.Menu;
import com.enigma.warung_lauk_online.repository.MenuRepository;
import com.enigma.warung_lauk_online.service.MenuService;
import com.enigma.warung_lauk_online.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository menuRepository;
    private final ValidationUtil validationUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Menu create(Menu menu) {
        validationUtil.validate(menu);
        return menuRepository.saveAndFlush(menu);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Menu> getAllMenu() {
        return menuRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Menu findByName(String name) {
        return menuRepository.findMenuByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Menu getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Transactional(readOnly = true)
    public Menu findByIdOrThrowNotFound(String id) {
        return menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UpdateMenuRequest menuRequest) {
        validationUtil.validate(menuRequest.getId());
        findByIdOrThrowNotFound(menuRequest.getId());

        menuRepository.updateMenu(menuRequest.getId(), menuRequest.getName(), menuRequest.getPrice());

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMenuById(String id) {
        validationUtil.validate(id);
        findByIdOrThrowNotFound(id);
        menuRepository.deleteMenuById(id);

    }
}
