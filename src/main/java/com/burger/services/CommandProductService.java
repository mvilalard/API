package com.burger.services;

import com.burger.models.Command;
import com.burger.models.CommandProduct;
import com.burger.repositories.CommandProductRepository;
import com.burger.repositories.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandProductService {

    @Autowired
    private CommandProductRepository cpr;

    public List<CommandProduct> getAll() {
        return cpr.findAll();
    }

    public CommandProduct getOne(int id) {
        return cpr.findById(id).orElse(null);
    }

    public List<CommandProduct> getByCommandId(int id)  {
        return cpr.findByCommandId(id);
    }

    public CommandProduct save(CommandProduct commandProduct) {
        return cpr.save(commandProduct);
    }

    public List<CommandProduct> saveAll(List<CommandProduct> commandProducts) {
        return cpr.saveAll(commandProducts);
    }
}
