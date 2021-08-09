package com.bista.inventory.controller;




import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bista.inventory.model.Item;
import com.bista.inventory.services.ItemService;

@Controller
public class ItemController {

	// Private field
	@Autowired
	private ItemService itemService;
	
	/*
	 * Controller to handle get request for add item 
	 * redirect user to the add item page.
	 */
	@GetMapping("/item")
	public ModelAndView showAddItemPage(@ModelAttribute("item") Item item, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addItem"); // Set the view name
		
		return mv;
	}
	
	/*
	 * Controller to handle post request for add item
	 * add the item in the database if item is valid
	 */
	@PostMapping("/item")
	public String saveItem(@Valid @ModelAttribute("item") Item item, BindingResult result, @RequestParam("picture") MultipartFile file, RedirectAttributes rAtt) {
		
		if(result.hasErrors()) {
			// If error exist in the item field, redirect to the add item page with error message
			return "/addItem";
		}else {
			try {
				// Save the item in the database
				itemService.saveItem(item, file);
			} catch (Exception e) {
				rAtt.addAttribute("msg", "Image format not supported");
				return "redirect:/item";
			}
			rAtt.addAttribute("msg", "Item added successfully");
			return "redirect:/item";
		}
			
	}
	
	
	/* 
	 * Controller to handle get request for edit item
	 * redirect the user to the edit item page.
	 */
	@GetMapping("/editItem")
	public ModelAndView showEditItemPage(@ModelAttribute("item") Item item, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editItem");
		
		return mv;
	}
	
	/*
	 * Controller to handle post request for edit item.
	 */
	@PostMapping("/updateItem")
	public String updateItem(@Valid @ModelAttribute("item") Item item, BindingResult result, @RequestParam("picture") MultipartFile file, RedirectAttributes rAtt) {
		
		if(result.hasErrors()) {
			// Redirect the user to edit item page, if error exist in the item fields. 
			return "/editItem";
		}else {
			try {
				// Update item info in the database 
				itemService.updateItem(item, file);
			} catch (Exception e) {
				rAtt.addAttribute("msg", "Image format not supported");
				return "redirect:/editItem";
			}
			
			rAtt.addAttribute("msg", "Item updated successfully");
			return "redirect:/editItem?success";
		}
			
	}
	
	// Controller to handle get mapping for view item.
	// Redirect the user to view item page.
	@GetMapping("/viewItem/{id}")
	public ModelAndView viewItemById(@PathVariable(value="id") long id) {
		ModelAndView mv = new ModelAndView();
		Item item = itemService.findItemById(id);
		mv.setViewName("preview");
		mv.addObject("item", item);
		
		return mv;
	}
	
	// Controller to handle get request to get specific item
	@GetMapping("/getItem/{id}")
	public ModelAndView getItemById(@PathVariable(value="id") long id) {
		ModelAndView mv = new ModelAndView();
		Item item = itemService.findItemById(id);
		mv.setViewName("editItem");// Set the view name
		mv.addObject("item", item);// Add an item to the model and view object
		
		return mv;
	}
	
	// Controller to handle get mapping for delete item.
	@GetMapping("/deleteItem/{id}")
	public String deleteItemById(@PathVariable(value="id") long id) {
		itemService.deleteItemById(id);	
		
		return "redirect:/";
	}
	
}
