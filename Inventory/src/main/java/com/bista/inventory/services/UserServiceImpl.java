package com.bista.inventory.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bista.inventory.dao.UserRepository;
import com.bista.inventory.dto.UserRegistrationDto;
import com.bista.inventory.model.User;
import com.bista.inventory.model.Role;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public  UserServiceImpl(UserRepository repo) {
		super();
		this.repo = repo;
	}
	
	@Override
	public User save(UserRegistrationDto user) {
		User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(),
				             passwordEncoder.encode(user.getPassword()), Arrays.asList(new Role("USER_ROLE")));
		if(repo.findByEmail(user.getEmail()) == null) {
			return repo.save(newUser);
		}else {
			return null;
		}
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username and/or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(
			Collection<com.bista.inventory.model.Role> roles) {
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}



}
