package com.burger.repositories;

import com.burger.models.Menu;
import com.burger.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findByPromotionId(int id_promotion);
    List<Menu> findByHighlight(int highlight);
    List<Menu> findByAvailable(int available);
}
