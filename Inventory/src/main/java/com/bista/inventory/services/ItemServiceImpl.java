package com.bista.inventory.services;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.bista.inventory.dao.ItemRepository;
import com.bista.inventory.model.Item;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;

//	public void saveItem(Item item) throws Exception {
//		
//		itemRepository.save(item);
//	}

	@Override
	public List<Item> findAllItems() {
		return itemRepository.findAll();
	}
	

	
	
	public void saveItem(Item item, MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		String dir1 = "/images/";
		String dir2 = "src/main/resources/static/images/";
		String image = dir1 + fileName;
		String fielExt = getFileExtension(fileName);
		if(checkFileExtension(fielExt)) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(dir2 + fileName);
			Files.write(path, bytes);
			

			item.setImage(image);
			itemRepository.save(item);
		}
	}
	
	private boolean checkFileExtension(String fileExt) {
		
		String[] ext = {"jpg", "jpeg", "png", "gif"};
		List<String> allowedExt = new ArrayList<String>(Arrays.asList(ext));
		
		return allowedExt.contains(fileExt);
	}

	private String getFileExtension(String fileName) {
		int index = fileName.lastIndexOf(".");
		
		String extension = fileName.substring(index +1);
		
		return extension;
	}


	public Item findItemById(long id) {
		Optional<Item> oItem = itemRepository.findById(id);
		Item item = new Item();
		
		if(oItem.isPresent()) {
			item = oItem.get();
		}else {
			throw new RuntimeException("Employee not found");
		}
			
		return item;
	}




	@Override
	public void updateItem(Item item) {
		if(itemRepository.existsById(item.getId())) {
			itemRepository.save(item);
		}else {
			throw new RuntimeException("record not found");
		}
		
	}




	@Override
	public void deleteItemById(long id) {
		if(itemRepository.existsById(id)) {
			itemRepository.deleteById(id);
		}else {
			throw new RuntimeException("record not found");
		}
		
	}
	
	

	
	
}
