package com.burger.repositories;

import com.burger.Embeddable.ListProductKey;
import com.burger.models.ListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListProductRepository extends JpaRepository<ListProduct, ListProductKey> {

}
