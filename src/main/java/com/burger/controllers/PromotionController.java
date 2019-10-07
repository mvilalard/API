package com.burger.controllers;

import com.burger.models.Promotion;
import com.burger.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path ="/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping(path="/")
    @ResponseBody
    public List<Promotion> getAll() {
        return promotionService.getAll();
    }

    @GetMapping(path="/{id}")
    @ResponseBody
    public Promotion getOne(@PathVariable int id) {
        return promotionService.getOne(id);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public Promotion create(@RequestBody Promotion promotion) {
        return promotionService.save(promotion);
    }

    @PutMapping(value = "/")
    @ResponseBody
    public Promotion update(@RequestBody Promotion promotion) {
        return promotionService.save(promotion);
    }

    @DeleteMapping(path="/{id}")
    @ResponseBody
    public void delete(@PathVariable int id) {
        promotionService.deleteById(id);
    }
}
