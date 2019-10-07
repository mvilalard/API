package com.burger.services;

import com.burger.models.Menu;
import com.burger.models.Promotion;
import com.burger.repositories.MenuRepository;
import com.burger.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> getAll() { return promotionRepository.findAll(); }

    public Promotion getOne(int id) { return promotionRepository.findById(id).orElse(null); }

    public Promotion save(Promotion promotion) { return promotionRepository.save(promotion); }

    public void delete(Promotion promotion) { promotionRepository.delete(promotion); }

    public void deleteById(int id) { promotionRepository.deleteById(id); }
}
