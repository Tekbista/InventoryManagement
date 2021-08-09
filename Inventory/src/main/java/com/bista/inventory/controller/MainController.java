package com.bista.inventory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.bista.inventory.model.Item;
import com.bista.inventory.services.ItemService;


@Controller
public class MainController {

	@Autowired
	private ItemService itemService;
	
	/*
	 *  Controller to handle get request for login
	 *  redirect the user to login page
	 */
	@GetMapping("/login")
	public ModelAndView showLoginPage() {		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("login");
		
		return mView;
	}
	
	/*
	 * Controller to handle get request for home page 
	 * redirect the user to home page
	 */
	
	@GetMapping("/")
	public ModelAndView showHomePage() {
		
		List<Item> items = itemService.findAllItems();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		mv.addObject("items", items);
		
		return mv;
	}
	
	// Sort the item by name
	@GetMapping("/sortByName")
	public ModelAndView sortByName() {
		List<Item> items = new ArrayList<>();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		items = itemService.sortItemsByName();
		mv.addObject("items", items);
		
		return mv;
	}
	
	// Sort the item by quantity
	@GetMapping("/sortByQuantity")
	public ModelAndView sortByQuantity() {
		List<Item> items = new ArrayList<>();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		items = itemService.sortItemsByQuantity();
		mv.addObject("items", items);
		
		return mv;
	}
	
	/* 
	 * Controller to handle get mapping for item search
	 * redirect the user to home page with item searched if found
	 */
	@GetMapping("/search")
	public ModelAndView searchItem(@RequestParam("search") String search) {
		
		List<Item> items = itemService.findItemByName(search);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		if(items.size() > 0) {
			mv.addObject("items", items);
		}
		
		return mv;
	}

	

	
	
}
