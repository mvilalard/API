package com.burger.repositories;

import com.burger.models.Menu;
import com.burger.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByPromotionId(int id_promotion);
    List<Product> findByHighlight(int highlight);
    List<Product> findByAvailable(int available);
}
