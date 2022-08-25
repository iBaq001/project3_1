package com.amr.project.service.repository;

import com.amr.project.model.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface ItemRepo extends CrudRepository<Item, Long> {

    Page<Item> findItemByName(String name, Pageable pageable);


    Page<Item> findAll(Pageable pageable);




}
