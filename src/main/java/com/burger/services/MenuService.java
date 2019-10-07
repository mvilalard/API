package com.burger.services;

import com.burger.models.ListProduct;
import com.burger.models.Menu;
import com.burger.models.Product;
import com.burger.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ProductService productService;

    public List<Menu> getAll() { return menuRepository.findAll(); }

    public List<Menu> getByHighlight(int highlight) { return menuRepository.findByHighlight(highlight); }

    public List<Menu> getByAvailable(int available) { return menuRepository.findByAvailable(available); }

    public Menu getBasicOne(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    public Menu getOne(int id) {
        Menu menu = menuRepository.findById(id).orElse(null);

        if(menu != null) {
            List<Product> products = new ArrayList<>();
            for(ListProduct lp : menu.getProductList()) {
                Product p = lp.getProduct();
                p.setPrice(lp.getPrice());
                p.setPosition(lp.getPosition());
                products.add(p);
            }

            menu.setProducts(products);
            menu.setProductList(null);
        }

        return menu;
    }

    public List<Menu> getByPromotionId(int id_promotion) { return menuRepository.findByPromotionId(id_promotion); }

    public Menu save(Menu menu) { return menuRepository.save(menu); }

    public void delete(Menu menu) { menuRepository.delete(menu); }

    public void deleteById(int id) { menuRepository.deleteById(id); }
}
