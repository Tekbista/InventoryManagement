package com.bista.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bista.inventory.model.Item;
import com.bista.inventory.services.ItemService;


@Controller
public class MainController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/login")
	public ModelAndView showLoginPage() {		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("login");
		
		return mView;
	}
	
	
	
	@GetMapping("/")
	public ModelAndView showHomePage(RedirectAttributes rAttributes) {
		
		List<Item> items = itemService.findAllItems();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		mv.addObject("items", items);
		
		return mv;
	}
	

	

	
	
}
