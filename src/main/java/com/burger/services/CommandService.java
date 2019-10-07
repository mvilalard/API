package com.burger.services;

import com.burger.models.*;
import com.burger.models.Currency;
import com.burger.repositories.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CommandService {

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private CommandProductService cps;

    @Autowired
    private CurrencyService currencyService;

    public List<Command> getAll() {
        return commandRepository.findAll();
    }

    public Command getOne(int id) {
        Command command = commandRepository.findById(id).orElse(null);

        if(command != null) {
            command.setProducts(null);
            try {
                Currency currency = currencyService.getCurrency();

                for (Map.Entry<String, BigDecimal> entry : currency.getRates().entrySet()) {
                    currency.getRates().put(entry.getKey(), entry.getValue().multiply(BigDecimal.valueOf(command.getTotal())));
                }

                command.setCurrency(currency);

            } catch (IOException e) {
                e.printStackTrace();
            }


            List<CommandProduct> cpList = cps.getByCommandId(command.getId());
            List<Menu> menus = new ArrayList<>();
            List<Product> products = new ArrayList<>();

            for(int i = 0; i < cpList.size(); i++) {
                CommandProduct cp = cpList.get(i);

                if(cp.getMenuId() == null) {
                    products.add(cp.getProduct());
                    continue;
                }

                Menu menu = menuService.getBasicOne(cp.getMenuId());

                if(menu != null) {
                    menu.setProductList(null);

                    List<Product> p = new ArrayList<>();

                    for(int j = 0; j < menu.getSize(); j++) {
                        cp = cpList.get(i + j);
                        p.add(cp.getProduct());

                    }
                    i += menu.getSize() - 1;

                    menu.setProducts(p);
                    menus.add(menu);
                }
            }

            command.setMenuList(menus);
            command.setProductList(products);
        }

        return command;
    }

    public List<Command> getByDone(int done) {
        return commandRepository.findByDone(done);
    }

    public Command save(Command command) {

        Command cmd = commandRepository.save(command);

        if(cmd != null) {
            if(command.getProducts() != null) {
                List<CommandProduct> cp = new ArrayList<>();
                for(CommandProduct commandProduct : command.getProducts()) {
                    cp.add(cps.save(commandProduct));
                }
                cmd.setProducts(cp);
            }
        }

        return cmd;
    }

    public void delete(Command command) { commandRepository.delete(command); }

    public void deleteById(int id) { commandRepository.deleteById(id); }
}
