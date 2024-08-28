package com.wmsdemo.controller;


import com.wmsdemo.common.Result;
import com.wmsdemo.entity.Menu;
import com.wmsdemo.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public Result list(@RequestParam String roleId) {
        return Result.success(menuService.lambdaQuery().like(Menu::getMenuright, roleId).list());
    }
}
