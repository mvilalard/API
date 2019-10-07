package com.burger.repositories;

import com.burger.models.CommandProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandProductRepository extends JpaRepository<CommandProduct, Integer> {
    List<CommandProduct> findByCommandId(int id);
}
