package com.bista.inventory.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bista.inventory.dto.UserRegistrationDto;
import com.bista.inventory.model.User;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDto user);
}
