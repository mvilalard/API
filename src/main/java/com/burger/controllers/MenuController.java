package com.burger.controllers;

import com.burger.models.Menu;
import com.burger.models.Product;
import com.burger.repositories.MenuRepository;
import com.burger.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path ="/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(path="/")
    @ResponseBody
    public List<Menu> getAll() {
        return menuService.getAll();
    }


    @GetMapping(path="/{id}")
    @ResponseBody
    public Menu getOne(@PathVariable int id) {
        return menuService.getOne(id);
    }

    @GetMapping(path="/highlight")
    @ResponseBody
    public List<Menu> getByHighlight() {
        return menuService.getByHighlight(1);
    }

    @GetMapping(path="/available")
    @ResponseBody
    public List<Menu> getByAvailable() {
        return menuService.getByAvailable(1);
    }

    @GetMapping("/promotion/{id}")
    @ResponseBody
    public List<Menu> getByPromotion(@PathVariable("id") int id) {
        return menuService.getByPromotionId(id);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public Menu create(@RequestBody Menu menu) {
        return menuService.save(menu);
    }

    @PutMapping(value = "/")
    @ResponseBody
    public Menu update(@RequestBody Menu menu) {
        return menuService.save(menu);
    }

    @DeleteMapping(path="/{id}")
    @ResponseBody
    public void delete(@PathVariable int id) {
        menuService.deleteById(id);
    }
}
