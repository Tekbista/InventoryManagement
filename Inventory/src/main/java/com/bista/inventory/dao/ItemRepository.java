package com.bista.inventory.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bista.inventory.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findByItemNameContains(String item);
	
}
