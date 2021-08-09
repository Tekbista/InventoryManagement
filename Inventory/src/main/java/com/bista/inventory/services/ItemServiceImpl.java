package com.bista.inventory.services;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bista.inventory.algorithm.MergeSort;
import com.bista.inventory.algorithm.QuickSort;
import com.bista.inventory.dao.ItemRepository;
import com.bista.inventory.model.Item;


@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;
	private MergeSort mergeSort = new MergeSort();
	private QuickSort quickSort = new QuickSort();



	@Override
	public List<Item> findAllItems() {
		List<Item> list = new ArrayList<>();
		list = itemRepository.findAll(); 
		return list;
	}
	

	
	
	
	public void saveItem(Item item, MultipartFile file) throws Exception {
		//Get the original file name from the file that user uploaded
		String fileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		
		// Give unique name to the file uploaded by user.
		String uniqueImageName = generateUniqueFileName(fileName);
		
		// Set the image path to store in database
		String imagePath = "/images/" + uniqueImageName;
		// Get the file extension
		String fielExt = getFileExtension(fileName);
		
		// Check if the file have valid extension 
		if(checkFileExtension(fielExt)) {
			if(fileSize > 0 && fileSize < 5000000) {
				byte[] bytes = file.getBytes();
				
				// Set the image path to write image in the project directory
				Path path = Paths.get("src/main/resources/static/images/" + uniqueImageName);
				Files.write(path, bytes);
				

				item.setImage(imagePath);
				itemRepository.save(item);
			}else {
				throw new RuntimeException("File size too large. Upload file less than 5Mb");
			}
			
		}else {
			throw new RuntimeException("File format not supported");
		}
	}
	
	// Check if the file extension is valid or not
	private boolean checkFileExtension(String fileExt) {
		
		String[] ext = {"jpg", "jpeg", "png", "gif"};
		List<String> allowedExt = new ArrayList<String>(Arrays.asList(ext));
		
		return allowedExt.contains(fileExt);
	}

	// Get the file extension for file validation
	private String getFileExtension(String fileName) {
		int index = fileName.lastIndexOf(".");
		
		String extension = fileName.substring(index +1);
		
		return extension;
	}
	
	// Generate unique file name 
	public String generateUniqueFileName(String fileName) {
		int m = (int) Math.pow(10, 10 -1);
		//Generate 10 digit random number
		Long num = (long) (m + new Random().nextInt(9 * m));
		String qunqueNumString = num.toString();
		
		int index = fileName.lastIndexOf(".");
		
		return fileName.substring(0, index) + qunqueNumString + fileName.substring(index);
	}

	// Find the item that match the item id in database
	public Item findItemById(long id) {
		Optional<Item> oItem = itemRepository.findById(id);
		Item item = new Item();
		
		// If item is present in the database get the item else throw exception
		if(oItem.isPresent()) {
			item = oItem.get();
		}else {
			throw new RuntimeException("Employee not found");
		}
			
		return item;
	}




	@Override
	public void updateItem(Item item, MultipartFile file){
		if(itemRepository.existsById(item.getId())) {
			Item oldiItem = findItemById(item.getId());
			// Get the old image path
			String imageDirString = "src/main/resources/static" + oldiItem.getImage();
			try {
				// Save the item info in the database
				saveItem(item, file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			deleteFile(imageDirString); // Delete the old image file in the images directory.
		}else {
			
		}
		
	}




	@Override
	public void deleteItemById(long id) {
		if(itemRepository.existsById(id)) {
			Item item = findItemById(id);
			if(item != null ) {
				String imageDirString = "src/main/resources/static" + item.getImage();
				deleteFile(imageDirString); // Delete the image file in the images directory.
				
				//Delete the item in database
				itemRepository.deleteById(id);
			}
			
			
		}else {
			throw new RuntimeException("record not found");
		}
		
	}




	@Override
	public List<Item> findItemByName(String itemName) {
		// Fetch all the items that match the name 
		List<Item> items = itemRepository.findByItemNameContains(itemName);
		
		// if there exist item(s) return the list of items.
		if(items != null) {
			return items;
		}else {
			return null;
		}
		
		
	}
	
	// Method to delete the file from specific directory
	public void deleteFile(String fileDIr) {
		File file = new File(fileDIr);
		
		file.delete();// Delete the file
	}





	@Override
	public List<Item> sortItemsByName() {
		List<Item> items = new ArrayList<Item>();
		
		items = itemRepository.findAll();
		return mergeSort.sort(items);
	}


	@Override
	public List<Item> sortItemsByQuantity() {
		List<Item> items = new ArrayList<Item>();
		
		items = itemRepository.findAll();
		return quickSort.sort(items);
	}
	

	
	
}
