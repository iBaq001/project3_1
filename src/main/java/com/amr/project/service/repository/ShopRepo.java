package com.amr.project.service.repository;

import com.amr.project.model.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface ShopRepo extends CrudRepository<Shop, Long> {

    Page<Shop> findAll(Pageable pageable);

}
