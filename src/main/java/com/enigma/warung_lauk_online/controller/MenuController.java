package com.enigma.warung_lauk_online.controller;

import com.enigma.warung_lauk_online.constant.APIUrl;
import com.enigma.warung_lauk_online.constant.ResponseMessage;
import com.enigma.warung_lauk_online.dto.request.UpdateMenuRequest;
import com.enigma.warung_lauk_online.dto.response.CommonResponse;
import com.enigma.warung_lauk_online.dto.response.MenuResponse;
import com.enigma.warung_lauk_online.entity.Menu;
import com.enigma.warung_lauk_online.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.MENU_API)
public class MenuController {
    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<CommonResponse<Menu>> createNewMenu(@RequestBody Menu menu) {
        Menu newMenu = menuService.create(menu);

        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newMenu)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Menu>>> getAllMenu(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "mobilePhoneNo",required = false) String phone,
            @RequestParam(name = "member", required = false) Boolean member
    ) {
        List<Menu> allMenu = menuService.getAllMenu();

        CommonResponse<List<Menu>> response = CommonResponse.<List<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(allMenu)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_NAME)
    public ResponseEntity<CommonResponse<Menu>> getByName(@PathVariable String name) {
        Menu menu = menuService.findByName(name);

        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(menu)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<MenuResponse>> updateMenu(@RequestBody UpdateMenuRequest menuRequest) {
         menuService.update(menuRequest);

        CommonResponse<MenuResponse> response = CommonResponse.<MenuResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteMenuById(@PathVariable String id) {
        menuService.deleteMenuById(id);

        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }
}
