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
import com.bista.inventory.services.UserService;

@Controller
@RequestMapping("/registration" )
public class UserRegistrationController {

	// Private field
	private UserService userService;

	// Parameterized constructor
	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	// Controller to redirect the user to the registration page
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
	
	// Controller that handles the post mapping for user registration 
	@PostMapping
	public String registerNewUser(@Valid @ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) {
		
		if(result.hasErrors()) {
			/*
			 * If error exist in from submission, redirect 
			 * user to the registration page with error message
			 */
			return "registration";
		}else {
			/*
			 *  Save the user in the database and redirect user
			 *   to registration page with success message
			 */
			if(userService.save(registrationDto) != null) {
				return "redirect:/registration?success";
			}else {
				/*
				 * redirect user to registration page with user already exist message.
				 */
				return "redirect:/registration?userexist";
			}
			
			
		}
			
	}
}
