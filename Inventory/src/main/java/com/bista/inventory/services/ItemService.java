package com.bista.inventory.services;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.bista.inventory.model.Item;

public interface ItemService {
	List<Item> findAllItems();
	void saveItem(Item item, MultipartFile file) throws Exception;
	Item findItemById(long id);
	void updateItem(Item item);
	void deleteItemById(long id);
}
