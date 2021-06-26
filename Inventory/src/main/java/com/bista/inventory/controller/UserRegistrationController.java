package com.bista.inventory.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bista.inventory.dto.UserRegistrationDto;
import com.bista.inventory.model.User;
import com.bista.inventory.services.UserService;

@Controller
@RequestMapping("/registration" )
public class UserRegistrationController {

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public ModelAndView showRegistrationPage() {		
		ModelAndView mView = new ModelAndView();
		mView.setViewName("registration");
		
		return mView;
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@PostMapping
	public String registerNewUser(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) {
		
		if(result.hasErrors()) {
			return "registration";
		}else {
			if(userService.save(registrationDto) != null) {
				return "redirect:/registration?success";
			}else {
				return "redirect:/registration?userexist";
			}
			
			
		}
			
	}
}
